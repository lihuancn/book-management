package cn.huanlingli.util;

import cn.huanlingli.config.ContentType;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public final class ResponseUtil {

    public static void ChangeContentType(HttpServletResponse resp,
                                         ContentType contentType,
                                         StandardCharsets charset) {
        resp.setContentType(String.format("%s; charset=%s", contentType.ToString(), charset.toString()));
    }

}
