package com.example.demo;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 邮件工具类
 */
public class MailUtil {
    //邮箱验证码

    public static void sendEmail(){
        // 不要使用SimpleEmail,会出现乱码问题
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：，普通qq号只能是smtp.qq.com ；
            email.setHostName("smtp.163.com");
            //设置需要鉴权端口
            email.setSmtpPort(465);
            //开启 SSL 加密
            email.setSSLOnConnect(true);
            // 字符编码集的设置
            email.setCharset("utf-8");
            // 收件人的邮箱
            email.addTo("1249279630@qq.com");
            // 发送人的邮箱
            email.setFrom("15989566325@163.com");
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和得到的授权码
            email.setAuthentication("15989566325@163.com", "gyfitedu");
            email.setSubject("你对象");
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg("：郑星星，你爸爸唐博洋：");
            // 发送
            email.send();

            System.out.println ( "邮件发送成功!" );
        } catch (EmailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println ( "邮件发送失败!" );
        }
    }
}
