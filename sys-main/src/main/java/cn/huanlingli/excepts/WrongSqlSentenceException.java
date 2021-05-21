package cn.huanlingli.excepts;

/**
 * SQL语句错误异常
 *
 * @author vorbote thills@vorbote.cn
 */
public class WrongSqlSentenceException extends RuntimeException {
    public WrongSqlSentenceException() {
        super();
    }

    public WrongSqlSentenceException(String message) {
        super(message);
    }
}
