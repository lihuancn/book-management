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
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/",
            "/index.jsp",
            "/login.jsp",
            "/register.jsp",
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

        var path = req.getRequestURI().replaceFirst("/sys-main", "");

        if (EXCLUDE_PATHS.contains(path)) {
            chain.doFilter(request, response);
        }

        var accessKey = req.getHeader("Access-Key");

        if (accessKey != null && !StringUtil.IsBlank(accessKey)) {
            try {
                var info = util.Info(accessKey);
                var nbf = info.getNotBefore().getTime() / 1000;
                var now = System.currentTimeMillis() / 1000;
                if (nbf - now < 300) {
                    var newKey = util.Renew(accessKey);
                    resp.setHeader("Access-Key", newKey);
                    log.info("旧Token「{}」即将过期，更换为新Token「{}」", accessKey, newKey);
                }

                chain.doFilter(req, resp);
            } catch (AlgorithmMismatchException e) {
                log.error("The algorithm stated in the token's header it's not equal to the one defined in the JWTVerifier.");
                FrontEndNoticeUtil.Alert(resp, "算法不匹配，请重新登录申请Token");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } catch (SignatureVerificationException e) {
                log.error("The signature is invalid.");
                FrontEndNoticeUtil.Alert(resp, "签名错误，请重新登录申请Token");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } catch (TokenExpiredException e) {
                log.error("The token has expired.");
                FrontEndNoticeUtil.Alert(resp, "令牌已过期，请重新登录申请Token");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } catch (InvalidClaimException e) {
                log.error("A claim contained a different value than the expected one.");
                FrontEndNoticeUtil.Alert(resp, "无效的载荷，请重新登录申请Token");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }
}
