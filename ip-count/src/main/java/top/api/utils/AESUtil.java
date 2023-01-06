package top.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import top.api.exception.ControllerException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author ：smallkinghjm
 * @description：TODO
 * @date ：2022/3/2 11:20
 */
@Slf4j
public class AESUtil {

    public static final String AESKey = "OG4vNnNUQUZPWHI1aUtBZ2d5ZTdRUT09";

    /**
     * AES加密
     *
     * @param content 明文
     * @param key     秘钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception {
        // 将返回的加密过的 byte[] 转换成Base64编码字符串 ！！！！很关键
        String s = "";
        try {
            s = base64ToString(AES_ECB_Encrypt(content.getBytes(), key.getBytes()));
        }catch (Exception e){
            log.error("AES加密出错！！！");
            throw new Exception();
        }
        return s;
    }

    /**
     * AES解密
     *
     * @param content Base64编码的密文
     * @param key     秘钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key) {
        // stringToBase64() 将 Base64编码的字符串转换成 byte[] !!!与base64ToString(）配套使用
        try {
            byte[] base64 = stringToBase64(content);
            byte[] bytes = AES_ECB_Decrypt(base64, key.getBytes());
            String result = new String(bytes);
            return result.replaceAll("\"","");
        } catch (Exception e) {
            log.error("AES解密出错！！！");
            throw new ControllerException(Code.CODE_ERR,"token is err");
        }

    }

    private static byte[] AES_ECB_Encrypt(byte[] content, byte[] keyBytes) throws Exception {
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new Exception();
        }
    }

    private static byte[] AES_ECB_Decrypt(byte[] content, byte[] keyBytes) throws Exception {
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new Exception();
        }
    }

    /**
     * 字符串装换成 Base64
     */

    public static byte[] stringToBase64(String key) throws Exception {

        byte[] bytes = new byte[0];
        try {
            bytes = Base64.decodeBase64(key.getBytes());
        } catch (Exception e) {
           // throw new RuntimeException(e);
            log.error("字符串装换成 Base64出错!");
        }

        return bytes;
    }

    /**
     * Base64装换成字符串
     */
    public static String base64ToString(byte[] key) throws Exception {
        return new Base64().encodeToString(key);
    }

    /**
     * 生成密钥,需要再次base64编码
     * @return
     */
    public static String getAESKey(){
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
            kg.init(128);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String secret = Base64.encodeBase64String(b);

            return secret;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("没有此算法");
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("开始测试");
        String encrypt = encrypt("hello", AESKey);
        System.out.println(encrypt);
    }
}

