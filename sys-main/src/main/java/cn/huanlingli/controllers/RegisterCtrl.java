package cn.huanlingli.controllers;

import cn.huanlingli.config.ContentType;
import cn.huanlingli.config.DbEngine;
import cn.huanlingli.dao.DbUtil;
import cn.huanlingli.util.ResponseUtil;
import cn.vorbote.commons.DatabaseUtil;
import cn.vorbote.commons.HashUtil;
import cn.vorbote.commons.StringUtil;
import cn.vorbote.commons.enums.EncryptMethod;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(urlPatterns = "/api/register", name = "RegisterCtrl")
@Slf4j
public class RegisterCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseUtil.ChangeContentType(response,ContentType.HTML,StandardCharsets.UTF_8);
        response.getWriter().write("<script>alert('请求方式错误！')</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseUtil.ChangeContentType(response, ContentType.HTML, StandardCharsets.UTF_8);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(StringUtil.IsBlank(username)&&StringUtil.IsBlank(password)){
            response.getWriter().write("<script>alert('用户名和密码不能为空！')</script>");
        }else{
            password = HashUtil.Encrypt(EncryptMethod.SHA_256,password);
            log.info("password:{}",password);
            try {
                DbUtil dbUtil = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138", 3306, "book_management", "root", "255839lH");
                var data = DatabaseUtil.ConvertList(dbUtil.ExecQuery(String.format("select username from bm_user where username = '%s';",username))).get(0);
                int size = data.keySet().size();
               if (size==0){
                   DatabaseUtil.ConvertList(dbUtil.ExecQuery(String.format("insert into bm_user(username,password) values('%s','%s');",username,password)));
               }else{
                   response.getWriter().write("<script>alert('用户名已存在！')</script>");
               }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}