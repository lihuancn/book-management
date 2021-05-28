package cn.huanlingli.util;

import cn.huanlingli.config.ContentType;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 为{@code HttpServletResponse}设置ContentType属性
 *
 * @author vorbote thills@vorbote.cn
 */
public final class ResponseUtil {

    /**
     * 修改{@code HttpServletResponse}的ContentType属性
     *
     * @param resp Response对象
     * @param contentType ContentType属性值
     * @param charset 编码字符集
     */
    public static void ChangeContentType(HttpServletResponse resp,
                                         ContentType contentType,
                                         Charset charset) {
        resp.setContentType(String.format("%s; charset=%s", contentType.ToString(), charset.toString()));
    }

}
