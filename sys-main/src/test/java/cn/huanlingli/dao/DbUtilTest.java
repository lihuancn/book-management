package cn.huanlingli.dao;

import cn.huanlingli.bean.OrderRentDetail;
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

public class DbUtilTest {
    @Test
    public void Test01() {
        try {
            DbUtil util = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138",
                    3306, "book_management", "root", "255839lH");
            final ResultSet bm_admin = util.Select().Table("bm_admin").ExecQuery();
            final List<Map<String, Object>> maps = DatabaseUtil.ConvertList(bm_admin);
            maps.forEach(System.out::println);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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

    @Test
    public void Test04() {
        try {
            DbUtil util = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138",
                    3306, "book_management", "root", "255839lH");
            int result = util.ExecUpdate("insert into bm_book(book_name, author, price, stock) values ('资本论', '卡尔·马克思', 19.98, 100);");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Test05(){
        try {
            DbUtil util = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138", 3306, "book_management", "root", "255839lH");
            int s = util.Insert("order_id", "book_id").Table("bm_order_rent_detail").Values(new OrderRentDetail(1, 3323, 4432)).ExecUpdate();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
