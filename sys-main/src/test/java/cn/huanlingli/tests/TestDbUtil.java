package cn.huanlingli.tests;

import cn.huanlingli.config.DbEngine;
import cn.huanlingli.dao.DbUtil;
import cn.vorbote.commons.DatabaseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TestDbUtil {
    @Test
    public void Test01() {
        // try {
        //     DbUtil util = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138",
        //             3306, "book_management", "root", "255839lH");
        //     final ResultSet bm_admin = util.Select().Table("bm_admin").ExecSQL();
        //     final List<Map<String, Object>> maps = DatabaseUtil.ConvertList(bm_admin);
        //     maps.forEach(System.out::println);
        // } catch (ClassNotFoundException | SQLException e) {
        //     e.printStackTrace();
        // }
    }

    @Test
    public void Test02() {
        String fmt = "Hello %s World";
        final String format = String.format(fmt, "111");
        System.out.println(format);
    }

    @Test
    public void Test03() {

        Object o = new ArrayList<Integer>();
        // System.out.println(o.getClass());

        Map<String, Object> map = new HashMap<>();
        map.put("hello", o);
        map.put("111", 111);
        map.put("222", "This is a string");

        for (String s : map.keySet()) {
            Object demo = map.get(s);
            System.out.println(demo.getClass());
        }
    }
}
