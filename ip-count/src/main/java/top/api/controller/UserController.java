package top.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.api.domain.IpInfo;
import top.api.domain.User;
import top.api.service.UserService;
import top.api.utils.*;


import javax.crypto.MacSpi;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Msg login(@RequestBody User user, HttpServletRequest request) throws Exception {
        // 校验用户 合格为null
        Msg userCheck = UserCheck.loginCheck(user, request,redisTemplate);
        if (userCheck != null){return userCheck;}

        return userService.login(user,request);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Msg register(@RequestBody User user, HttpServletRequest request) throws Exception {
        // 校验用户 合格为null
        Msg userCheck = UserCheck.registerCheck(user, request,redisTemplate);
        if (userCheck != null){return userCheck;}

        return userService.register(user,request);
    }

    /**
     * 重置密码
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/resetPassword")
    public Msg resetPassword(@RequestBody User user, HttpServletRequest request) throws Exception {
        // 校验用户 合格为null
        Msg userCheck = UserCheck.registerCheck(user, request,redisTemplate);
        if (userCheck != null){return userCheck;}


        return userService.resetPassword(user,request);
    }


    /**
     * 返回用户信息
     * @param request
     * @return
     */
    @GetMapping("/userInfo")
    public Msg userInfo(HttpServletRequest request) throws Exception {
        Integer id = (Integer) request.getAttribute("id");
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");
        return userService.userInfo(id, ipInfo);
    }

}
