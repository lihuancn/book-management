package cn.huanlingli.dao;

import cn.huanlingli.config.DbEngine;
import cn.vorbote.commons.StringUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 这个工具可以用于进行数据库操作
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
    private static DbUtil _instance = new DbUtil();

    // 本类的PreparedStatement对象
    private static String statement;

    // 数据源对象
    private DataSource dataSource;
    // 数据库连接对象
    private Connection connection;

    // 单例模式下，需要屏蔽原先的构造函数
    private DbUtil() {
    }

    public static DbUtil GetInstance(DbEngine engine, String host, int port,
                                     String dbName, String username, String password) throws SQLException, ClassNotFoundException {
        _instance.loadConnection(engine, host, port, dbName, username, password);
        return _instance;
    }

    // region Getter 和 Setter
    /**
     * 获取数据源
     * @return 数据源对象
     */
    private DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 设置数据源
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

    public DbUtil Select(String... fields) throws SQLException {
        var strBuilder = new StringBuilder("");
        var field = "*";
        if (fields != null && fields.length > 0) {
            for (int i = 0; i < fields.length; ++i) {
                strBuilder.append(fields[i]);
                if (i != fields.length - 1) {
                    strBuilder.append(", ");
                }
            }
            field = strBuilder.toString();
        }
        statement = "select " + field;
        return _instance;
    }

    public DbUtil FromTable(String tableName) {
        statement += " from " + tableName;
        return _instance;
    }

    public DbUtil Where(String conditions) {
        statement += " where " + conditions;
        return _instance;
    }

    public ResultSet ExecSQL() throws SQLException {
        log.info(statement);
        var stmt = getConnection().prepareStatement(statement);
        statement = "";
        return stmt.executeQuery();
    }
}
