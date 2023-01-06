package top.api.utils;


import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

public class EmailCodeUtil {

    private static Random RANDOM = new Random();
    private static final String CODE = "1234567890";

    /**
     * 1失败 成功返回验证码
     * 发送邮箱验证码
     * @param email 邮箱
     * @param info 标题
     * @param size 验证码长度
     * @return 验证码
     */
    public static int sendAuthCodeEmail(String email,String info,Integer size) {
        HtmlEmail mail = new HtmlEmail();
        String code = generateVerifyCode(size);
        try {
            /*发送邮件的服务器 126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com*/
            mail.setHostName("smtp.qq.com");
            /*不设置发送的消息有可能是乱码*/
            mail.setCharset("UTF-8");
            /*IMAP/SMTP服务的密码*/
            mail.setAuthentication("2631348819@qq.com", "xugbohksnvezebfg");
            /*发送邮件的邮箱和发件人*/
            mail.setFrom("2631348819@qq.com", "ip统计助手");
            /*使用安全链接*/
            mail.setSSLOnConnect(true);
            /*接收的邮箱*/
            mail.addTo(email);
            /*设置邮件的主题*/
            mail.setSubject(info);
            /*设置邮件的内容*/
            mail.setMsg("尊敬的用户:你好! "+info +"为:" + code + "(有效期为一分钟)");

            mail.send();//发送
        } catch (Exception e) {
            return 1;
        }
        return Integer.parseInt(code);
    }

    /**
     * 生成随机验证码
     * @param number 几位数
     * @return
     */
    public static String generateVerifyCode(int number) {
        String code = "";
        for (int i = 0; i < number; i++) {
            int index = RANDOM.nextInt(CODE.length());
            code += CODE.charAt(index);
        }
        return code;
    }

}
