package top.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.api.domain.IpInfo;
import top.api.domain.User;

import javax.servlet.http.HttpServletRequest;

public class UserCheck {

    /**
     * 校验用户,如果合格返回null
     * @param user
     * @return
     */
    public static Msg loginCheck(User user, HttpServletRequest request,RedisTemplate redisTemplate){
        if (user.getUserName() == null || user.getPassword() == null){
            return new Msg(Code.CODE_NO,"请输入账号或密码!",null);
        }else if (! user.getUserName().matches("^\\d{5,12}@[qQ][qQ]\\.(com|cn)$")){
            return new Msg(Code.CODE_NO,"账号邮箱格式错误!",null);
        }else if (! user.getPassword().matches("^\\w{6,12}$")){
            return new Msg(Code.CODE_NO,"密码长度请在6-12之间!",null);
        } else if (user.getCheckCode() == null || user.getCheckCode().length() < 4) {
            return new Msg(Code.CODE_NO,null,null);
        }

        // 将redis中的图形验证码获取出来
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");
        String checkCode =  redisTemplate.opsForValue().get("checkCode"+ipInfo.getIp()+request.getHeader("User-Agent"))+"";

        // 清除验证码,防止复用
        redisTemplate.delete("checkCode"+ipInfo.getIp()+request.getHeader("User-Agent"));


        if (checkCode == null){
            return new Msg(Code.CODE_NO,"请先获取验证码!",null);
        } else if ( !user.getCheckCode().equalsIgnoreCase(checkCode)) {
            return new Msg(Code.CODE_NO,"图形验证码错误!",null);
        }

        return null;
    }

    /**
     * 校验用户,如果合格返回null
     * @param user
     * @return
     */
    public static Msg registerCheck(User user, HttpServletRequest request,RedisTemplate redisTemplate){
        if (user.getUserName() == null || user.getPassword() == null){
            return new Msg(Code.CODE_NO,"请输入账号或密码!",null);
        }else if (! user.getUserName().matches("^\\d{5,12}@[qQ][qQ]\\.(com|cn)$")){
            return new Msg(Code.CODE_NO,"账号邮箱格式错误!",null);
        }else if (! user.getPassword().matches("^\\w{6,12}$")){
            return new Msg(Code.CODE_NO,"密码长度请在6-12之间!",null);
        } else if (user.getCheckCode() == null || user.getCheckCode().length() < 4) {
            return new Msg(Code.CODE_NO,null,null);
        }

        // 将redis中的邮箱验证码获取出来
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");
        String checkCode = (String) redisTemplate.opsForHash().get("emailCode"+ipInfo.getIp()+request.getHeader("User-Agent"),"emailCode");


        if (checkCode == null){
            return new Msg(Code.CODE_NO,"请先获取验证码!",null);
        } else if ( !user.getCheckCode().equalsIgnoreCase(checkCode)) {
            return new Msg(Code.CODE_NO,"邮箱验证码错误!",null);
        }

        // 验证码成功在清除验证码,防止复用
        redisTemplate.opsForHash().delete("emailCode"+ipInfo.getIp()+request.getHeader("User-Agent"),"emailCode");


        return null;
    }
}
