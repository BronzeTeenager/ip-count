package top.api.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.api.domain.IpInfo;
import top.api.utils.AESUtil;
import top.api.utils.Code;
import top.api.utils.Msg;
import top.api.utils.OnlineUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("online")
public class OnlineController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OnlineUtil onlineUtil;

    /**
     * 添加在线统计(每3分钟请求一次)
     * @param appId
     * @return
     */
    @GetMapping("/count")
    public Msg count(String appId, HttpServletRequest request){
        if (appId.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        appId = appId.replace(' ', '+');

        appId = AESUtil.decrypt(appId, AESUtil.AESKey);

        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");

        Boolean onlineFlag = onlineUtil.addOnline(OnlineUtil.key +"online" + appId, ipInfo.getIp() + request.getHeader("User-Agent"));

        // 将appId放入redis set集合中给定时任务遍历删除(目前不会删除该)
        redisTemplate.opsForSet().add("set_appId",OnlineUtil.key +"online" + appId);

        return onlineFlag ? Msg.success("ok"):Msg.error("no");
    }


    @GetMapping("/getCount")
    public Msg getCount(String appId){
        if (appId.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        appId = appId.replace(' ', '+');

        appId = AESUtil.decrypt(appId, AESUtil.AESKey);

        // 获取3分钟内，发送过心跳包的用户数量，也就是在线用户的数量
        Long count = onlineUtil.count(OnlineUtil.key +"online" + appId, Duration.ofMinutes(3));
        Map<String,Long> map = new HashMap<>();
        map.put("count",count);

        return Msg.success("ok",map);
    }
}
