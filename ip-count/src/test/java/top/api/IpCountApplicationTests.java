package top.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.api.dao.AppDao;
import top.api.dao.IpDao;
import top.api.service.AppService;
import top.api.service.IpService;
import top.api.utils.*;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class IpCountApplicationTests {

    @Autowired
    private IpDao ipDao;

    @Autowired
    private IpService ipService;


    @Autowired
    private AppService appService;

    @Test
    void contextLoads() {


    }

    @Test
    void encrypt() throws Exception {
        /*String encrypt = AESUtil.encrypt("123456789779798", AESUtil.AESKey);

        System.out.println(encrypt)*/
        ;

        AESUtil.decrypt("cafxQ08aYa5pKAegEeIwOQ==", AESUtil.AESKey);

        String sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());

        System.out.println(sdf);

    }

    @Test
    void md5() {
        String time = new SimpleDateFormat("yyyy年MM月dd日HH时").format(System.currentTimeMillis());
        String str = "2022年10月10日14时/user/login";

        System.out.println(MD5Utils.stringToMD5(str));
    }


    @Test
    void AES() throws Exception {
        String encrypt = AESUtil.encrypt("{\"admin\":\"4564564\"}", AESUtil.AESKey);
        System.out.println("{\"admin\":\"4564564\"}");
        String decrypt = AESUtil.decrypt(encrypt, AESUtil.AESKey);

        System.out.println(decrypt);
    }

    @Test
    void RSA() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String str = "hello world";

        String encrypt = RSAUtils.privateEncrypt(str, RSAUtils.getPrivateKey(RSAUtils.PRIVATE_KEY));

        System.out.println("公钥加密: " + encrypt);

        System.out.println(RSAUtils.privateDecrypt(encrypt, RSAUtils.getPrivateKey(RSAUtils.PRIVATE_KEY)));
    }

    @Test
    void token() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "root");
        map.put("password", "admin");

        String token = TokenUtils.getToken(map);

        System.out.println(token);

        String tokenStr = TokenUtils.getTokenStr(token);

        System.out.println(tokenStr);
    }

    @Test
    void tokenCheck() throws Exception {
        String str = "\"M9hYrywqgsz3jC9vOdSikjdgcxggNIZQpcMBXCXrg4ncB07QhlxGAiojxjrg5liJQkzCXT5BMZHw9LvtlOd8n3G-3RaiyDs78X8YYcfUM1EqmUGNjUlZemY90RnQuyO51r03lY6pwBCLmhxQZDEoSvsZbQsCPnWlwGWJmDWibbE";
        boolean flag = TokenUtils.TokenCheck(str);
        System.out.println(flag);
    }

    @Test
    void redisSet(@Autowired RedisTemplate redisTemplate){
        redisTemplate.opsForValue().set("name","zhangsan",10, TimeUnit.SECONDS);
    }

    @Test
    void redisGet(@Autowired RedisTemplate redisTemplate){
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void redisHset(@Autowired RedisTemplate redisTemplate){
        redisTemplate.opsForHash().put("info","b","1234655");
    }

    @Test
    void redisHget(@Autowired RedisTemplate redisTemplate){
        Object o = redisTemplate.opsForHash().get("checkCode", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4449.6 Mobile Safari/537.36");
        System.out.println(o);
    }

    @Test
    void email() throws Exception {
        String str = "p5h6N3wN+QlQ33WsjIW+JA==";

        // String 转 byte数组
        byte [] strByte = str.getBytes(StandardCharsets.UTF_8);

        String toString = AESUtil.base64ToString(strByte);

        System.out.println(toString);

        byte[] bytes = toString.getBytes(StandardCharsets.UTF_8);

        System.out.println(AESUtil.base64ToString(bytes));
    }

    @Test
    void update(){
        boolean flag = appService.updateIPRequest();
        System.out.println(flag);
    }
}

