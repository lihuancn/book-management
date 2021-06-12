package cn.huanlingli.filter;

import cn.huanlingli.config.Helpers;
import cn.huanlingli.util.FrontEndNoticeUtil;
import cn.vorbote.commons.StringUtil;
import cn.vorbote.simplejwt.AccessKeyUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AccessKeyFilter implements Filter {

    private static final AccessKeyUtil util = Helpers.GetAccessKeyUtil();
    // 需要将不进行AccessKey验证的路径写入这个List
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/",
            "/index.html",
            "/index.html",
            "/login.html",
            "/login.html",
            "/register.html",
            "/register.html",
            "/api/no-auth/login",
            "/api/no-auth/register"
    );

    @Override
    public void init(FilterConfig config)
            throws ServletException {
        log.info("AccessKeyFilter 已经加载成功...");
    }

    @Override
    public void destroy() {
        log.info("AccessKeyFilter 已经销毁成功...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 读取系统路径并删除sys-main
        var path = req.getRequestURI().replaceFirst("/sys-main", "");

        // 如果路径属于不需要检查的路径则直接放行
        if (EXCLUDE_PATHS.contains(path)) {
            chain.doFilter(request, response);
        }

        // 从请求头中获取AccessKey
        var accessKey = req.getHeader("Access-Key");

        // 确保AccessKey不为空
        if (!StringUtil.IsBlank(accessKey)) {
            try {
                var info = util.Info(accessKey);
                var nbf = info.getNotBefore().getTime() / 1000; // 获取过期时间
                var now = System.currentTimeMillis() / 1000;    // 获取当前时间
                if (nbf - now < 300) {  // 判断当前时间到过期时间是否在5分钟（300秒）内
                    // 原令牌即将过期，申请新令牌
                    var newKey = util.Renew(accessKey);
                    resp.setHeader("Access-Key", newKey);
                    log.info("旧Token「{}」即将过期，更换为新Token「{}」", accessKey, newKey);
                }

                // 完成验证，放行
                chain.doFilter(request, response);
            } catch (AlgorithmMismatchException e) {
                log.error("The algorithm stated in the token's header it's not equal to the one defined in the JWTVerifier.");
                FrontEndNoticeUtil.Alert(resp, "算法不匹配，请重新登录申请Token");
                req.getRequestDispatcher("/login.html").forward(req, resp);
            } catch (SignatureVerificationException e) {
                log.error("The signature is invalid.");
                FrontEndNoticeUtil.Alert(resp, "签名错误，请重新登录申请Token");
                req.getRequestDispatcher("/login.html").forward(req, resp);
            } catch (TokenExpiredException e) {
                log.error("The token has expired.");
                FrontEndNoticeUtil.Alert(resp, "令牌已过期，请重新登录申请Token");
                req.getRequestDispatcher("/login.html").forward(req, resp);
            } catch (InvalidClaimException e) {
                log.error("A claim contained a different value than the expected one.");
                FrontEndNoticeUtil.Alert(resp, "无效的载荷，请重新登录申请Token");
                req.getRequestDispatcher("/login.html").forward(req, resp);
            }
        }
    }
}
