package top.api.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 在线用户统计
 *
 * @author Administrator
 */
@Component
public class OnlineUtil {

    // key前缀
    public static final String key = "zset_";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加用户在线信息
     *
     * @param userId
     * @return
     */
    public Boolean addOnline(String key, String userId) {
        return this.stringRedisTemplate.opsForZSet().add(key, userId.toString(), Instant.now().toEpochMilli());
    }

    /**
     * 获取一定时间内，在线的用户数量
     *
     * @param duration
     * @return
     */
    public Long count(String key, Duration duration) {
        LocalDateTime now = LocalDateTime.now();
        return this.stringRedisTemplate.opsForZSet().count(key,
                now.minus(duration).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * 获取所有在线过的用户数量，不论时间
     *
     * @return
     */
    public Long count(String key) {
        return this.stringRedisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 清除超过一定时间没在线的用户数据
     *
     * @param duration
     * @return
     */
    public Long clear(String key, Duration duration) {
        return this.stringRedisTemplate.opsForZSet().removeRangeByScore(key, 0,
                LocalDateTime.now().minus(duration).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}