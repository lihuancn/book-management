package cn.huanlingli.config;

import cn.huanlingli.dao.DbUtil;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.simplejwt.JwtUtil;
import com.google.gson.Gson;

import java.sql.SQLException;

public final class Helpers {

    private Helpers() {
    }

    private static final String SECRET = "";
    private static final String ISSUER = "";

    private static final Gson gson = new Gson();
    private static final JwtUtil jwt = new JwtUtil(SECRET, ISSUER);
    private static final AccessKeyUtil accessKey = new AccessKeyUtil(SECRET, ISSUER);

    public static Gson GetGson() {
        return gson;
    }

    public static JwtUtil GetJWT() {
        return jwt;
    }

    public static AccessKeyUtil GetAccessKeyUtil() {
        return accessKey;
    }

    public static DbUtil GetDbUtil() throws SQLException, ClassNotFoundException {
        return DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138", 3306, "book_management", "root", "255839lH");
    }

}
