package top.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.api.dao.IpDao;

import java.text.SimpleDateFormat;

@SpringBootTest
public class IpDaoTest {

    @Autowired
    private IpDao ipDao;

    @Test
    void selectIpCountAndDate(){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        Integer ipCount = ipDao.selectIpCountAndDate(7, "2022-11-19");
        System.out.println(ipCount);
    }

    @Test
    void selectRequestSumAndDate(){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        Integer requestSum = ipDao.selectRequestSumAndDate(7, "2022-11-19");
        System.out.println(requestSum);
    }

    @Test
    void selectIpCount(){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        Integer ipCount = ipDao.selectIpCount(7);
        System.out.println(ipCount);
    }

    @Test
    void selectRequestSum(){
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        Integer requestSum = ipDao.selectRequestSum(6);
        System.out.println(requestSum);
    }
}
