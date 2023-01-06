package top.api.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * token工具类
 */
@Slf4j
public class TokenUtils {
    
    // 设置过期时间 1天
    private static final long time = 24 * 60 * 60 * 1000;


    /**
     * 获取token
     * @param
     * @return
     */
    public static String getToken(Map<String,Object> map) throws Exception {
        String data;
        try {
            JSONObject json = new JSONObject();

            json.putAll(map);

            long nowTime = System.currentTimeMillis();

            json.put("time",nowTime+time);

            String publicEncrypt = RSAUtils.publicEncrypt(json.toJSONString(), RSAUtils.getPublicKey(RSAUtils.PUBLIC_KEY));

            data = publicEncrypt;
        } catch (Exception e) {
            log.error("RSA公钥加密失败---> "+ e);
            throw new Exception();
        }
        return data;
    }

    /**
     * token解密
     * @param token
     * @return
     * @throws Exception
     */
    public static String getTokenStr(String token) throws Exception {
        String data;
        try {
            data = RSAUtils.privateDecrypt(token,RSAUtils.getPrivateKey(RSAUtils.PRIVATE_KEY));
        } catch (Exception e) {
            log.error("RSA私钥加密失败---> "+ e);
            throw new Exception();
        }
        return data;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static boolean TokenCheck(String token) throws Exception {
        String data;
        try {
            data = RSAUtils.privateDecrypt(token, RSAUtils.getPrivateKey(RSAUtils.PRIVATE_KEY));
        } catch (Exception e) {
            log.error("RSA私钥加密失败,token无效---> "+ e);
            return false;
        }

        JSONObject json = JSONObject.parseObject(data);
        long time = (long) json.get("time");

        long nowTime = System.currentTimeMillis();

        if (nowTime > time){
            return false;
        }

        return true;
    }
}
