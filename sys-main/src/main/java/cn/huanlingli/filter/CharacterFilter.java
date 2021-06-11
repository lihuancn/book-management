package cn.huanlingli.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于将{@code Request}和{@code Response}的编码方式修改为{@code UTF-8}
 *
 * @author vorbote thills@vorbote.cn
 */
@Slf4j
public class CharacterFilter implements Filter {

    @Override
    public void init(FilterConfig config)
            throws ServletException {
        log.info("CharacterFilter 已经加载成功...");
    }

    @Override
    public void destroy() {
        log.info("CharacterFilter 已经销毁成功...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }
}
