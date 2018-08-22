package com.cszt.jobs;

import com.cszt.domain.MailSender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * 最基本的邮件发送代码
 *
 * @author Steven
 *
 */
public class SimpleSendMailDemo {
    public static void main(String[] args) throws Exception {
        // 创建邮件的发送过程中用到的主机和端口号的属性文件
        Properties pro = new Properties();
        // 设置邮件发送方的主机地址如果是163邮箱，则为smtp.163.com
        // 如果是其他的邮箱可以参照http://wenku.baidu.com/link?url=Cf-1ggeW3e7Rm9KWfz47UL7vvkRpPxAKBlYoTSGpnK4hxpJDiQ0A4lRoPDncMlcMIvUpEn6PD0aObgm5zJaM7AOGkRdccSx6HDH2fSWkxIq这个文档
        pro.put("mail.smtp.host", "smtp.qq.com");
        // 设置发送邮件端口号
        pro.put("mail.smtp.port", "25");
        // 设置邮件发送需要认证
        pro.put("mail.smtp.auth", "true");

        // 创建邮件验证信息，即发送邮件的用户名和密码
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 重写验证方法，填写用户名，密码
                return new PasswordAuthentication("1612268911@qq.com", "xcpudycewrsbdajj");
            }
        };
        // 根据邮件会话 构建一个邮件的session
        Session sendMailSession = Session
                .getDefaultInstance(pro, authenticator);
        // 创建一个邮件消息
        Message message = new MimeMessage(sendMailSession);
        String nicName = MimeUtility.encodeText("呵呵");
        // 创建邮件发送者地址
        Address sourceAddress = new InternetAddress(nicName+"<1612268911@qq.com>");
        // 将原地址设置到消息的信息中
        message.setFrom(sourceAddress);
        // 创建邮件的接收者地址
        Address destAddress = new InternetAddress("3035796278@qq.com");
        // 将接收者的地址设置到消息的信息中
        message.setRecipient(Message.RecipientType.TO, destAddress);
        // 设置邮件的主题
        message.setSubject("Merry Christmas!");
        // 设置邮件的发送内容
        message.setContent("你好，圣诞节快乐！","text/templates;charset=utf-8");
        // 可以设置邮件的发送时间(就是对方看邮件发送的时间)
         String sendDate = "2013-12-23 17:55:00";
         Date date = new
                 SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sendDate);
         message.setSentDate(date);
        // 发送邮件
        Transport.send(message);
        System.out.println("发送成功...");
    }
}
