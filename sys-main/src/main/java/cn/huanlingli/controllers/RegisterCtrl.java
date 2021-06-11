package cn.huanlingli.controllers;

import cn.huanlingli.config.DbEngine;
import cn.huanlingli.config.Helpers;
import cn.huanlingli.dao.DbUtil;
import cn.huanlingli.util.FrontEndNoticeUtil;
import cn.vorbote.commons.DatabaseUtil;
import cn.vorbote.commons.HashUtil;
import cn.vorbote.commons.StringUtil;
import cn.vorbote.commons.enums.EncryptMethod;
import cn.vorbote.simplejwt.AccessKeyUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

/**
 * 用于注册的Servlet
 *
 * @author lihuan lihuan@huanlingli.cn
 */
@WebServlet(urlPatterns = "/api/no-auth/register", name = "RegisterCtrl")
@Slf4j
@SuppressWarnings("all")
public class RegisterCtrl extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontEndNoticeUtil.Alert(response, "请求方式错误");// 请求方式错误
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (StringUtil.IsBlank(username) && StringUtil.IsBlank(password)) {
            response.getWriter().write("<script>alert('用户名和密码不能为空！')</script>");
        } else {
            password = HashUtil.Encrypt(EncryptMethod.SHA_256, password);
            // log.info("password:{}", password);
            try {
                DbUtil dbUtil = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138", 3306, "book_management", "root", "255839lH");
                var data = DatabaseUtil.ConvertList(dbUtil.ExecQuery(String.format("select username from bm_user where username = '%s';", username))).get(0);
                int size = data.keySet().size();
                if (size == 0) {
                    DatabaseUtil.ConvertList(dbUtil.ExecQuery(String.format("insert into bm_user(username,password) values('%s','%s');", username, password)));
                } else {
                    response.getWriter().write("<script>alert('用户名已存在！')</script>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
