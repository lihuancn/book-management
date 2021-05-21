package cn.huanlingli.bean;

import org.junit.Test;

public class AdminTest {

    @Test
    public void Test01() {
        Admin admin = new Admin(1, "张三", "123456", "19918903791", "thills@vorbote.cn");
        System.out.println(admin.ToSQL());
    }


}