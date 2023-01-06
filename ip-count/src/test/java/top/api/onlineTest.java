package top.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.api.utils.OnlineUtil;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
public class onlineTest {
    @Resource
    private OnlineUtil onlineUtil;

    @Test
    public void test() {

        // ID为1的用户发送了心跳包
        boolean result = this.onlineUtil.addOnline("1",1+"");
        System.out.println("online=" + result);

        // 获取5分钟内，发送过心跳包的用户数量，也就是在线用户的数量
        Long count = this.onlineUtil.count("1",Duration.ofMinutes(5));
        System.out.println("oneline count=" + count);

        // 获取所有发送过心跳包的用户数量
        count = this.onlineUtil.count("1");
        System.out.println("all count=" + count);

        // 清除超过1天都没发送过心跳包的用户
        Long clear = this.onlineUtil.clear("1",Duration.ofDays(1));
        System.out.println("clear=" + clear);
    }

    @Test
    void test2(){
        // 获取5分钟内，发送过心跳包的用户数量，也就是在线用户的数量
        Long count = this.onlineUtil.count("online2",Duration.ofMinutes(5));
        System.out.println("oneline count=" + count);

        // 获取所有发送过心跳包的用户数量
        count = this.onlineUtil.count("online2");
        System.out.println("all count=" + count);
    }


    @Test
    void test3(){
        // 清除超过1天都没发送过心跳包的用户
        Long clear = this.onlineUtil.clear("zset_online2",Duration.ofMinutes(1));
        System.out.println("clear=" + clear);
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now().minus(Duration.ofMinutes(1)));
        System.out.println(LocalDateTime.now().minus(Duration.ofMinutes(1)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Test
    void time(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(LocalDateTime.now().minus(Duration.ofMinutes(1)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()>1670334596321L);
    }
}
