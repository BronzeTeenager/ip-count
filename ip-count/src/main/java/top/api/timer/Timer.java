package top.api.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.api.service.AppService;
import top.api.service.UserService;
import top.api.utils.OnlineUtil;

import java.time.Duration;
import java.util.Set;

/**
 * 定时类
 */
@Slf4j
@EnableScheduling //开启定时注解
@Component
public class Timer {

    @Autowired
    private AppService appService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private OnlineUtil onlineUtil;

/*    *//**
     * 将今日次数复制到昨日上
     * 将今日字段全部初始化为0
     *
     *
     * 每日 00:05 自动运行
     *//*
    @Scheduled(cron = "0 05 00 * * ?")
    public void updateIPRequestAndSet0(){
        boolean flag = appService.updateIPRequest();
        if (flag){
            log.info("定时器运行成功(将今日次数复制到昨日上,将今日字段全部初始化为0)");
        }else{
            log.warn("定时器运行失败(将今日次数复制到昨日上,将今日字段全部初始化为0)");
        }
    }*/

    /**
     * 将sign_in_status 全部初始化为 0
     *
     * 每日 00:00 自动运行
     */
    @Scheduled(cron = "0 00 00 * * ?")
    public void updateSignInStatus(){
        boolean flag = userService.updateSignInStatus0();
        if (flag){
            log.info("定时器运行成功(将sign_in_status全部初始化为0)");
        }else{
            log.warn("定时器运行失败(将sign_in_status全部初始化为0)");
        }
    }

    /**
     * 将redis中set集合删除
     *
     * 每日 03:30 自动运行
     */
    @Scheduled(cron = "0 30 3 * * ?")
    public void removeSetKey(){
        Boolean flag = true;
        try {
             flag = redisTemplate.delete("set_appId");
        } catch (Exception e) {
            log.error("删除set集合中set_appId的key失败--->{}",e.getMessage());
        }
        log.info("定时任务: 删除set集合中set_appId的key--->{}",flag);
    }

    /**
     * 移除redis中项目超过3分钟都没发送过心跳包的用户
     *
     * 每隔1分钟运行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void removeOnline(){
        try {
            //获取set集合中所有value
            Set<String> setNum = redisTemplate.opsForSet().members("set_appId");
            // 获取到zset中的key
            for (String s : setNum) {
                // 清除超过3分钟都没发送过心跳包的用户
                onlineUtil.clear(s,Duration.ofMinutes(3));
            }
        } catch (Exception e) {
            log.error("清空在线人数出错: "+e.getMessage());
        }
    }

/*    @Scheduled(fixedDelay = 2000)
    public  void time2(){
        System.out.println("我是定时器2");
    }


    @Scheduled(fixedDelay = 5000)
    public  void time3() throws InterruptedException {
        System.out.println("我是定时器3");
        Thread.sleep(10000);
    }*/
}
