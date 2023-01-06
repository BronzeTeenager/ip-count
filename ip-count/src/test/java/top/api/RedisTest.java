package top.api;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void redisHget() {
        Boolean emailCodeTimeFlag = redisTemplate.opsForHash().hasKey("emailCode" + "127.0.0.1", "emailCodeTime");
        if (emailCodeTimeFlag) {
            long emailCodeTime = Long.parseLong(redisTemplate.opsForHash().get("emailCode" + "127.0.0.1", "emailCodeTime")+"");
            System.out.println(emailCodeTime);
            System.out.println(System.currentTimeMillis());
            if (System.currentTimeMillis() < emailCodeTime){
                System.out.println("时间未过期不可以发送");
            }else{
                System.out.println("可以发送");
            }
        }else {
            System.out.println("过期了");
        }
    }

    @Test
    void Time(){
        System.out.println("dkjfkd");
        Set keys = redisTemplate.keys("zset*");
        System.out.println(keys);
    }

    @Test
    void add(){
        for (int i = 0; i < 1000000; i++) {
            stringRedisTemplate.opsForValue().set(i+"",i+"");
        }
    }

    @Test
    void time(){
        for (int i = 0; i < 1000000; i++) {
        }
    }
}
