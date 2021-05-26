package cn.huanlingli.util;

import cn.huanlingli.config.ContentType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class FrontEndNoticeUtil {

    /**
     * 将提醒消息通过{@code JavaScript}打印到前端
     *
     * @param resp Response对象
     * @param message 消息内容
     * @throws IOException 可能会抛出IO异常
     */
    public static void Alert(HttpServletResponse resp, String message) throws IOException {
        ResponseUtil.ChangeContentType(resp, ContentType.HTML, StandardCharsets.UTF_8);
        resp.getWriter().write(String.format("<script>alert('%s');</script>", message));
    }
}
