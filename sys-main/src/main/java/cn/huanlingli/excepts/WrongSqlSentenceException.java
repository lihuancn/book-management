package cn.huanlingli.excepts;

public class WrongSqlSentenceException extends RuntimeException {
    public WrongSqlSentenceException() {
        super();
    }

    public WrongSqlSentenceException(String message) {
        super(message);
    }
}
