package cn.huanlingli.controllers;

import cn.huanlingli.config.DbEngine;
import cn.huanlingli.dao.DbUtil;
import cn.vorbote.commons.DatabaseUtil;
import cn.vorbote.commons.HashUtil;
import cn.vorbote.commons.StringUtil;
import cn.vorbote.commons.enums.EncryptMethod;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登录请求
 *
 * @author vorbote thills@vorbote.cn
 */
@WebServlet(urlPatterns = "/api/login", name = "LoginCtrl")
@Slf4j
public class LoginCtrl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (StringUtil.IsBlank(username) && StringUtil.IsBlank(password)) {
            resp.getWriter().write("<script>alert('用户名或者密码不能为空！')</script>");
        } else {
            password = HashUtil.Encrypt(EncryptMethod.SHA_256, password);
            log.info("password: {}", password);
            try {
                DbUtil db = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138", 3306, "book_management", "root", "255839lH");
                var data = DatabaseUtil.ConvertList(db.ExecQuery(String.format("select username,password from bm_user where username = '%s' and password = '%s';", username, password))).get(0);
                int size = data.keySet().size();
                if (size >= 1) {
                    resp.getWriter().write("<script>alert('登录成功！')</script>");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            req.getRequestDispatcher(req.getContextPath()+"/");

        }
    }
}
