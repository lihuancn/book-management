package cn.huanlingli.excepts;

/**
 * 数据库引擎不支持异常
 *
 * @author vorbote thills@vorbote.cn
 */
public class UnsupportedEngineException extends RuntimeException {
    public UnsupportedEngineException() {
        super();
    }

    public UnsupportedEngineException(String message) {
        super(message);
    }
}
