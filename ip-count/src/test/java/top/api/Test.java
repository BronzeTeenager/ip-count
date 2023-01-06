package top.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import top.api.domain.IpInfo;
import top.api.utils.IpUtils;
import top.api.utils.MD5Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class Test {

    @Value("${checkCode}")
    private boolean checkCode;

    @org.junit.jupiter.api.Test
    void random() {
        System.out.println(checkCode);
    }

    @org.junit.jupiter.api.Test
    void checkDate() {
        boolean flag = isValidDate("2022-11");
        System.out.println(flag);
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    @org.junit.jupiter.api.Test
    void getDay() {

        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());

        List<String> dateList = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = year + "-" + month + "-" + i;
            dateList.add(aDate);
        }

        for (String date : dateList) {

            System.out.println(date);

            if (time.equals(date)){
                break;
            }
        }

    }

    @org.junit.jupiter.api.Test
    void getYesterday(){
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
        System.out.println(yesterday);
    }

    @org.junit.jupiter.api.Test
    void time(){
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        System.out.println(time);
    }

    @org.junit.jupiter.api.Test
    void md5(){
        String md5 = MD5Utils.stringToMD5("123456");
        System.out.println(md5);
    }
}


