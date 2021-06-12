package cn.huanlingli.controllers;

import cn.huanlingli.config.Helpers;
import cn.huanlingli.dao.DbUtil;
import cn.huanlingli.util.FrontEndNoticeUtil;
import cn.vorbote.commons.DatabaseUtil;
import cn.vorbote.commons.HashUtil;
import cn.vorbote.commons.StringUtil;
import cn.vorbote.commons.enums.EncryptMethod;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.simplejwt.config.TimeConst;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

/**
 * 处理登录请求
 *
 * @author vorbote thills@vorbote.cn
 */
@WebServlet(urlPatterns = "/api/no-auth/login", name = "LoginCtrl")
@Slf4j
public class LoginCtrl extends HttpServlet {

    private final static Gson gson = Helpers.GetGson();
    private final static AccessKeyUtil accessKeyUtil = Helpers.GetAccessKeyUtil();
    private static DbUtil db;

    static {
        try {
            db = Helpers.GetDbUtil();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (StringUtil.IsBlank(username) && StringUtil.IsBlank(password)) {
            FrontEndNoticeUtil.Alert(resp, "用户名或者密码不能为空");
        } else {
            password = HashUtil.Encrypt(EncryptMethod.SHA_256, password);
            log.info("password: {}", password);
            try {
                var data = DatabaseUtil.ConvertList(db.ExecQuery(StringUtil.Format("select count(1) from bm_user where username = '{}' and password = '{}';", username, password))).get(0);
                int size = data.keySet().size();
                if (size >= 1) {
                    var result = db.ExecQuery(StringUtil.Format("select status from bm_user where username = '{}'", username));
                    var status = result.getInt(0);
                    if (status == 1) {
                        var accessKey = accessKeyUtil.CreateToken(30 * TimeConst.MINUTE,
                                "user", username, null
                            /*new HashMap<>() {{
                                // use put(String, Object) to put data to hash map.
                            }}*/);
                        resp.addHeader("Access-Key", accessKey);
                        FrontEndNoticeUtil.Alert(resp, "登录成功！");
                    } else if (status == 2) {
                        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        FrontEndNoticeUtil.Alert(resp, "账户已被封禁，请稍后再试！");
                    } else {
                        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        FrontEndNoticeUtil.Alert(resp, "账户已被删除！");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            req.getRequestDispatcher(req.getContextPath() + "/");

        }
    }
}
