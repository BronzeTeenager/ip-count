package top.api.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 工具类
 */
public class Tools {

    /**
     * 获取今日日期 2022-11-20
     * @return
     */
    public static String getTodayDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
    }
    /**
     * 获取昨日日期 2022-11-19
     * @return
     */
    public static String getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}
