package cn.huanlingli;

import cn.vorbote.commons.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

@Slf4j
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
        log.info("Hello World");
    }
}
