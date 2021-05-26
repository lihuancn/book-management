package cn.huanlingli.config;

/**
 * 内容类型的枚举类，存储有常量值。
 *
 * @author vorbote thills@vorbote.cn
 */
public enum ContentType {
    // 纯文本
    HTML("text/html"), PLAIN_TEXT("text/plain"), XML("text/xml"),
    // 图片
    GIF("image/gif"), JPEG("image/jpeg"), PNG("image/png"),
    // applications
    XHTML("application/xhtml+xml"), APPLICATION_XML("application/xml"),
    ATOM_XML("application/atom+xml"), JSON("application/json"), PDF("application/pdf"),
    WORD("application/msword"), BINARY_STREAM("application/octet-stream"),
    ENC_TYPE("application/x-www-form-urlencoded"),

    FORM("multipart/form-data");

    private String value;

    ContentType() { }

    ContentType(String value) {
        this.value = value;
    }

    @Deprecated
    @Override
    public String toString() {
        return value;
    }

    public String ToString() {
        return value;
    }
}
