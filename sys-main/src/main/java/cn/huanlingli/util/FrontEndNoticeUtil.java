package cn.huanlingli.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 前端提醒工具，用于向前端输出相关消息
 *
 * @author vorbote thills@vorbote.cn
 */
public final class FrontEndNoticeUtil {

    /**
     * 将提醒消息通过{@code JavaScript}打印到前端
     *
     * @param resp Response对象
     * @param message 消息内容
     * @throws IOException 可能会抛出IO异常
     */
    public static void Alert(HttpServletResponse resp, String message) throws IOException {
        resp.getWriter().write(String.format("<script>alert('%s');</script>", message));
    }
}
