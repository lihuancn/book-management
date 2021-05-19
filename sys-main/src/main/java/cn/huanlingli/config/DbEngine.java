package cn.huanlingli.config;

/**
 * 建议除了MySQL v8.x版本使用{@code MYSQL}
 * @author vorbote theodore0126@vorbote.cn
 */
public enum DbEngine {

    MYSQL("com.mysql.jdbc.Driver"),
    MYSQL_8("com.mysql.cj.jdbc.Driver"),
    MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    // This variable is used to store the driver class name.
    private String value;

    DbEngine() { }

    DbEngine(String value) {
        this.value = value;
    }

    @Deprecated
    @Override
    public String toString() {
        return this.value;
    }

    public String ToString() {
        return this.value;
    }
}
