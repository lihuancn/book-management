package cn.huanlingli;

import cn.vorbote.commons.DateUtil;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

public class Draft {
    @Test
    public void Test01(){
        Date date = DateUtil.GetDate(2020, Calendar.JANUARY, 17);
        Calendar calendar = DateUtil.GetCalendar(date);
        calendar.add(Calendar.DAY_OF_MONTH,3);
        date = DateUtil.GetDate(calendar);
        System.out.println(date);
    }

    @Test
    public void Test02() {
        System.out.println(StandardCharsets.UTF_8);
    }
}
