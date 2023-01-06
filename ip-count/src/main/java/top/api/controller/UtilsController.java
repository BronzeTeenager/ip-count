package top.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.api.dao.UserDao;
import top.api.domain.IpInfo;
import top.api.domain.User;
import top.api.service.UserService;
import top.api.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Value("${checkCode}")
    private boolean checkCodeFlag;

    @Value("${notice.msg}")
    private String notice;

    @Value("${notice.status}")
    private String noticeStatus;

    /**
     * 生成字节流验证码
     */
/*    @GetMapping("/checkCode")
    public void checkCode(HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");

        ServletOutputStream outputStream = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(125, 45, outputStream, 4);


        // 将正确验证码存入session
        session.setAttribute("checkCode", checkCode);

        System.out.println(checkCode);
    }*/

    /**
     * 生成bas64验证码
     * @param response
     * @param
     * @return
     */
    @GetMapping("/checkCode")
    public String checkCode (HttpServletResponse response, HttpServletRequest request){
        response.setContentType("application/json; charset=utf-8");
        Map map = CaptchaPic.getCaptchaPic();

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("base64Img",map.get("base64Str"));

        String checkCode = (String) map.get("code");

        // 根据yml文件控制是否输出
        if (checkCodeFlag){
            System.out.println(checkCode);
        }

        // 将正确验证码存入redis 3分钟过期
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");

        redisTemplate.opsForValue().set("checkCode"+ipInfo.getIp()+request.getHeader("User-Agent"),checkCode,60*3,TimeUnit.SECONDS);

        return json.toJSONString();
    }

    /**
     * 发送邮箱验证码
     * @param email
     * @param request
     * @return
     */
    @PostMapping("/emailCode")
    public String emailCode(String email, HttpServletRequest request){
        if (! email.matches("^\\d{5,12}@[qQ][qQ]\\.(com|cn)$")){
            JSONObject json = new JSONObject();
            json.put("code", Code.CODE_NO);
            json.put("msg","邮箱格式错误!");
            return json.toJSONString();
        }
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");

        //判断是否在一分钟内发送过验证码
        Boolean emailCodeTimeFlag = redisTemplate.opsForHash().hasKey("emailCode" + ipInfo.getIp()+request.getHeader("User-Agent"), "emailCodeTime");
        if (emailCodeTimeFlag) {
            long emailCodeTime = Long.parseLong(redisTemplate.opsForHash().get("emailCode" +  ipInfo.getIp()+request.getHeader("User-Agent"), "emailCodeTime")+"");
            if (System.currentTimeMillis() < emailCodeTime){
                JSONObject json = new JSONObject();
                json.put("code", Code.CODE_NO);
                json.put("msg","一分钟只能发送一次哦!");
                return json.toJSONString();
            }
        }


        int emailCode = EmailCodeUtil.sendAuthCodeEmail(email, "验证码", 4);
        if (emailCode != 1){
            // 成功将验证码和有效时间戳保存redis 3分钟过期
            redisTemplate.opsForHash().put("emailCode"+ipInfo.getIp()+request.getHeader("User-Agent"),"emailCode",emailCode+"");
            redisTemplate.opsForHash().put("emailCode"+ipInfo.getIp()+request.getHeader("User-Agent"),"emailCodeTime",(System.currentTimeMillis()+(60*1000))+"");
            redisTemplate.expire("emailCode"+ipInfo.getIp()+request.getHeader("User-Agent"),60*3,TimeUnit.SECONDS);
        }else {
            // 失败
            JSONObject json = new JSONObject();
            json.put("code", Code.CODE_NO);
            json.put("msg","验证码发送失败!");
            return json.toJSONString();
        }
        JSONObject json = new JSONObject();
        json.put("code", Code.CODE_OK);
        json.put("msg","验证码发送成功!");
        return json.toJSONString();
    }


    /**
     * 签到接口
     * @param request
     * @return
     */
    @GetMapping("/signIn")
    public Msg signIn(HttpServletRequest request){
        String userId = request.getAttribute("id") + "";

        // 查询该用户是否今日签到过
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,userId);
        User dbUser = userService.getOne(queryWrapper);

        if (dbUser.getSignInStatus() != 0){
            Map<String, Integer> map = new HashMap<>();
            map.put("signInMoney",dbUser.getSignInStatus());
            return new Msg(Code.CODE_NO,"今日您已签到过了~",map);
        }

        //随机今日签到金额 最大金额为 5
        Random random = new Random();
        int index = random.nextInt(6);

        // 写入签到随机余额
        boolean flag = userService.updateSignInStatus(Integer.valueOf(userId), index);

        // 添加余额
        userService.updateMoney(Integer.valueOf(userId),dbUser.getMoney()+index);


        Map<String, Integer> map = new HashMap<>();
        map.put("signInMoney",index);
        return new Msg(flag?Code.CODE_OK:Code.CODE_NO,flag?"签到成功":"签到失败",map);
    }

    /**
     * 检测token是否有效
     * @param request
     * @return
     */
    @GetMapping("/checkToken")
    public Msg checkToken(HttpServletRequest request){
        String token = request.getHeader("token");

        if (token == null || "".equals(token)){
            return new Msg(Code.CODE_NO,null,null);
        }

        boolean flag = true;
        try {
             flag = TokenUtils.TokenCheck(token);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage()+"token检测无效!");
            return new Msg(Code.CODE_ERR,null,null);
        }

        return new Msg(flag?Code.CODE_OK: Code.CODE_NO,null,null);
    }

    /**
     * 获取公告
     * @return
     */
    @GetMapping("/notice")
    public Msg getNotice(){
        Map<String,String> map = new HashMap<>();
        map.put("msg",notice);
        map.put("status",noticeStatus);
        return Msg.success("ok",map);
    }
}
