package cn.huanlingli.tests;

import cn.huanlingli.config.DbEngine;
import cn.huanlingli.dao.DbUtil;
import cn.vorbote.commons.DatabaseUtil;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestDbUtil {
    @Test
    public void Test01() {
        try {
            DbUtil util = DbUtil.GetInstance(DbEngine.MYSQL, "1.14.150.138",
                    3306, "book_management", "root", "255839lH");
            final ResultSet bm_admin = util.Select().FromTable("bm_admin").ExecSQL();
            final List<Map<String, Object>> maps = DatabaseUtil.ConvertList(bm_admin);
            maps.forEach(System.out::println);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
