package cn.huanlingli.dao;

import cn.huanlingli.bean.Insertable;
import cn.huanlingli.config.DbEngine;
import cn.huanlingli.config.SortOrder;
import cn.huanlingli.excepts.UnsupportedEngineException;
import cn.huanlingli.excepts.WrongSqlSentenceException;
import cn.vorbote.commons.StringUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个工具可以用于进行数据库操作
 *
 * @author vorbote theodore0126@vorbote.cn
 */
@Slf4j
public final class DbUtil {

    // region 默认属性值 常量
    // 默认MySQL用户名
    private final static String DEFAULT_MYSQL_USERNAME = "root";
    // 默认MSSQL用户名
    private final static String DEFAULT_MSSQL_USERNAME = "sa";
    // 默认密码
    private final static String DEFAULT_PASSWORD = "123456";
    // endregion

    // 本类的实例
    private static final DbUtil _instance = new DbUtil();

    // 用于数据库操作的字符串
    private static String _statement;

    // 用于存储数据库引擎
    private DbEngine engine;

    // 数据源对象
    private DataSource dataSource;
    // 数据库连接对象
    private Connection connection;

    // 单例模式下，需要屏蔽原先的构造函数
    private DbUtil() {
    }

    /**
     * 获取当前数据连接工具对象实例
     *
     * @param engine   数据库引擎，支持{@code MS SQL Server}和{@code MySQL}
     * @param host     数据库
     * @param port     数据库端口
     * @param dbName   数据库名称
     * @param username 数据库用户名
     * @param password 数据库密码
     * @throws ClassNotFoundException 当没有加载驱动器类的时候会抛出磁异常
     * @throws SQLException           当无法加载数据库连接时
     */
    public static DbUtil GetInstance(DbEngine engine, String host, int port,
                                     String dbName, String username, String password)
            throws SQLException, ClassNotFoundException {
        // 加载数据库连接
        _instance.loadConnection(engine, host, port, dbName, username, password);
        return _instance;
    }

    // region Getter 和 Setter


    private DbEngine getEngine() {
        return engine;
    }

    private void setEngine(DbEngine engine) {
        this.engine = engine;
    }

    /**
     * 获取数据源
     *
     * @return 数据源对象
     */
    private DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 设置数据源
     *
     * @param dataSource 数据源对象
     */
    private void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取连接
     *
     * @return connection对象
     */
    private Connection getConnection() {
        return connection;
    }

    /**
     * 设置连接
     *
     * @param connection 连接对象
     */
    private void setConnection(Connection connection) {
        this.connection = connection;
    }
    // endregion

    /**
     * 加载数据库连接
     *
     * @param engine   数据库引擎，支持{@code MS SQL Server}和{@code MySQL}
     * @param host     数据库
     * @param port     数据库端口
     * @param dbName   数据库名称
     * @param username 数据库用户名
     * @param password 数据库密码
     * @throws ClassNotFoundException 当没有加载驱动器类的时候会抛出磁异常
     * @throws SQLException           当无法加载数据库连接时
     */
    private void loadConnection(DbEngine engine, String host, int port,
                                String dbName, String username, String password)
            throws ClassNotFoundException, SQLException {

        // 加载数据库驱动
        Class.forName(engine.ToString());

        // 填充默认信息
        if (StringUtil.IsBlank(username)) username =
                engine == DbEngine.MSSQL ? DEFAULT_MSSQL_USERNAME : DEFAULT_MYSQL_USERNAME;
        if (StringUtil.IsBlank(password)) password = DEFAULT_PASSWORD;

        // 连接数据库
        String dbUrl = null;
        switch (engine) {
            case MSSQL:
                dbUrl = String.format("jdbc:sqlserver://%s:%d;database=%s;", host, port, dbName);
                break;
            case MYSQL:
            case MYSQL_8:
                dbUrl = String.format("jdbc:mysql://%s:%d/%s", host, port, dbName);
                break;
        }

        // 加载Hikari数据源配置
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(100);
        config.setMinimumIdle(10);
        config.setConnectionTestQuery("select 1");

        // 设置数据源
        this.dataSource = new HikariDataSource(config);
        // 设置连接对象
        this.connection = this.dataSource.getConnection();
    }

    /**
     * 初始化查找参数
     *
     * @param fields 需要查询的字段
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Select(String... fields) {
        var strBuilder = new StringBuilder("select ");
        if (fields == null || fields.length == 0)
            strBuilder.append("*");
        else {
            int iMax = fields.length - 1;
            for (int i = 0; i < fields.length; ++i) {
                strBuilder.append(fields[i]);
                if (i != iMax)
                    strBuilder.append(", ");
            }
        }
        strBuilder.append(" from %s");
        _statement = strBuilder.toString();
        return _instance;
    }

    /**
     * 初始化插入参数
     *
     * @param fields    需要插入的字段名
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Insert(String... fields) {
        // 初始化StringBuilder
        var strBuilder = new StringBuilder("insert into %s");
        // 检查插入的字段名并进行自动拼接
        if (fields != null && fields.length != 0) {
            strBuilder.append("(");
            int iMax = fields.length - 1;
            for (int i = 0; i < fields.length; ++i) {
                strBuilder.append(fields[i]);
                if (i != iMax)
                    strBuilder.append(", ");
            }
            strBuilder.append(")");
        }

        strBuilder.append(" ");
        _statement = strBuilder.toString();
        return _instance;
    }

    /**
     * 将SQL语句初始化为{@code update}指令
     *
     * @param data 这个data应该是{@code key1='string_value1',key2=int_value2,...}的形式
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Update(String data) {
        // update bm_book set xx=xx where xxx;
        _statement = "update %s set " + data;
        return _instance;
    }

    /**
     * 将SQL语句初始化为{@code delete}指令
     *
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Delete() {
        _statement = "delete from %s ";
        return _instance;
    }

    /**
     * 设置作用的数据库表，其中在运行此方法前会自动检查当前SQL语句是否满足以下几种格式：
     * <ul>
     *     <li>{@code select xxx from }(大小写不敏感)</li>
     *     <li>{@code delete xxx from }(大小写不敏感)</li>
     *     <li>{@code update }(大小写不敏感)</li>
     * </ul>
     *
     * @param tableName 数据库中表的名称
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Table(String tableName) {
        // 当不满足以下sql语句时抛出异常
        if (!(_statement.matches("(select|SELECT)\\s([a-zA-Z0-9_,]+|\\*)\\s(from|FROM)\\s") ||
                _statement.matches("(delete|DELETE)\\s([a-zA-Z0-9_,]+|\\*)\\s(from|FROM)\\s") ||
                _statement.matches("(update|UPDATE)\\s") ||
                _statement.matches("(insert into|INSERT INTO)\\s")))
            throw new WrongSqlSentenceException("There is an SQL syntax error in your SQL sentence");

        // 满足以上sql语句，附加表名
        _statement = String.format(_statement, tableName);
        return _instance;
    }

    /**
     * 为插入语句设置的函数
     *
     * @param beans 可以插入的数据
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Values(Insertable... beans) {
        // 检查格式
        if (!_statement.matches("(insert into|INSERT INTO)\\s([a-zA-Z0-9_]+)\\s(values|VALUES)\\s"))
            throw new WrongSqlSentenceException("There is an SQL syntax error in your SQL sentence");

        var builder = new StringBuilder(_statement);
        int iMax = beans.length - 1;
        int i = 0;
        for (Insertable bean : beans) {
            builder.append(bean.ToSQL());
            if (i != iMax) {
                builder.append(", ");
            }
        }
        _statement = builder.toString();
        return _instance;
    }

    /**
     * 设置数据库条件
     *
     * @param conditions {@code where}后的内容，应该以{@code field1='string_value1'
     *                   and field2!=int_value2 or field3!=int_value3}
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Where(String conditions) {
        // 检查SQL语句
        if (!(_statement.matches("(select|SELECT)\\s([a-zA-Z0-9_,]+|\\*)\\s(from|FROM)\\s([a-zA-Z0-9_,]+)\\s") ||
                _statement.matches("(delete|DELETE)\\s(from|FROM)\\s([a-zA-Z0-9_,]+)\\s") ||
                _statement.matches("(update|UPDATE)\\s([a-zA-Z0-9_,]+)\\s(set|SET).+\\s")))
            throw new WrongSqlSentenceException("There is an SQL syntax error in your SQL sentence");

        _statement += "where " + conditions;
        return _instance;
    }

    /**
     * 设置排序条件
     *
     * @param field 根据制定的字段进行排序
     * @param order 可选择{@code SortOrder.ASCENDING}进行升序排列，选择{@code SortOrder.DESCENDING}
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil OrderBy(String field, SortOrder order) {
        if (_statement.matches("(select|SELECT)\\s([a-zA-Z0-9_,]+|\\*)\\s(from|FROM)\\s([a-zA-Z0-9_,]+)\\s.+"))
            _statement += "order by " + field + " " + order.ToString();
        return _instance;
    }

    /**
     * 设置查询条目数，该方法仅限{@code MySQL}使用
     *
     * @param offset 偏移量
     * @param length 查询长度
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Limit(int offset, int length) {
        if (engine == DbEngine.MSSQL) {
            throw new UnsupportedEngineException("This engine is not supported.");
        } else
            _statement += "limit " + offset + ", " + length;
        return _instance;
    }

    /**
     * 设置查询条目数，该方法仅限{@code SQL Server}使用
     *
     * @param length 查询长度
     * @return 该数据库查询工具的唯一实例
     */
    public DbUtil Limit(int length) {
        if (engine == DbEngine.MSSQL) {
            var builder = new StringBuilder(_statement);
            builder.insert(7, "top " + length);
            _statement = builder.toString();
        } else {
            throw new UnsupportedEngineException("This engine is not supported.");
        }
        return _instance;
    }

    /**
     * 执行查询
     *
     * @param query 查询语句
     * @return 查询出的结果集
     * @throws SQLException 可能产生SQL异常，将在产生时抛出
     */
    public ResultSet ExecQuery(String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        return stmt.executeQuery();
    }

    /**
     * 执行{@code update}、{@code insert}、{@code delete}语句
     *
     * @param update 更新操作语句
     * @return 查询出的结果集
     * @throws SQLException 可能产生SQL异常，将在产生时抛出
     */
    public int ExecUpdate(String update) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(update);
        return stmt.executeUpdate();
    }
}
