package top.api.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.Set;

/**
 * set集合
 */
@SpringBootTest
public class setTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void add(){
        Long add = redisTemplate.opsForSet().add("user", "lisi");
        System.out.println(add);
    }

    @Test
    void getAll(){
        Set<String> user = redisTemplate.opsForSet().members("user");
        System.out.println(user);
    }

    @Test
    void count(){
        Long size = redisTemplate.opsForSet().size("user");
        System.out.println(size);
    }

    @Test
    void remove(){
        Long num = redisTemplate.opsForSet().remove("user","lisi");
        System.out.println(num);
    }
}
