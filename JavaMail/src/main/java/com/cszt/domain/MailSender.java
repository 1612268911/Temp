/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MailSender
 * Author:   jj
 * Date:     2018/8/13 11:34
 * Description: 消息发送者实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.domain;

import com.cszt.config.GetConfigFile;
import com.cszt.util.AuthenticatorImpl;
import com.cszt.util.MailContentType;
import com.sun.mail.util.MimeUtil;
import com.sun.prism.impl.BaseResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息发送者实体〉
 *
 * @author jj
 * @create 2018/8/13
 * @since 1.0.0
 */
public class MailSender {
//    @Value("${mail.smtp.service}")
//    private String smtpService;
//
//    @Value("${mail.smtp.port}")
//    private String smtpPort;
//
//    @Value("${mail.from.smtp.pwd}")
//    private String smtpPwd;
//
//    @Value("${mail.from.address}")
//    private String address;
//
//    @Value("${mail.from.nicname}")
//    private String nicName1;

    private MailEntity mailEntity = new MailEntity();
    /**
     * 邮件标题
     */
    public MailSender setTitle(String title){
        mailEntity.setTitle(title);
        return this;
    }
    /**
     * 邮件内容
     */
    public MailSender setContent(String content){
        mailEntity.setContent(content);
        return this;
    }
    /**
     * 设置邮件格式
     */
    public MailSender setContenType(String contenType){
        mailEntity.setContentType(contenType);
        return this;
    }
    /**
     * 设置接收者集合
     */
    public MailSender setList(List<String> list) {
        mailEntity.setList(list);
        return this;
    }

    /**
     * 发送邮件
     */
    public void send(){
        Properties properties = null;
        try {
            if(StringUtils.isEmpty(mailEntity.getTitle())){
                throw new Exception("标题不能为空");
            }
            if(StringUtils.isEmpty(mailEntity.getContent())){
                throw new Exception("内容不能为空");
            }
            if(CollectionUtils.isEmpty(mailEntity.getList())){
                throw new Exception("收件人不能为空");
            }
            properties = new Properties();

            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.host","smtp.qq.com");//发件服务器
            properties.put("mail.smtp.port","25");//端口


//            properties.put("mail.user","1612268911@qq.com");
//            properties.put("mail.password","xcpudycewrsbdajj");//

//            String userName = properties.getProperty("mail.user");
//            String passWord = properties.getProperty("mail.password");
            //构建授权信息，进行smtp身份验证
//            Authenticator authenticator = new AuthenticatorImpl(userName,passWord);

            Authenticator authenticator = new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    // 重写验证方法，填写用户名，密码
                    return new javax.mail.PasswordAuthentication("1612268911@qq.com", "xcpudycewrsbdajj");
                }
            };
            //使用环境属性和授权信息创建会话
            Session mailSession = Session.getDefaultInstance(properties,authenticator);
            //创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            //发件人
            InternetAddress from = new InternetAddress("1612268911@qq.com");
            message.setFrom(from);

            //接收人
            List<String> target = mailEntity.getList();
            //设置接收人邮件地址
            InternetAddress to = new InternetAddress(target.get(0));

            message.setRecipient(Message.RecipientType.TO,to);
            //邮件标题
            message.setSubject(mailEntity.getTitle());
            //邮件类型
//            if(mailEntity.getContentType().equals(MailContentType.HTML)){
//                message.setContent(mailEntity.getContent(),mailEntity.getContentType());
//            }else{
//                message.setText(mailEntity.getContent(),mailEntity.getContentType());
//            }
            //附件
            Multipart multipart = addAttch("C:\\Users\\jj\\Desktop\\activiti.docx");

            //内容
            MimeBodyPart mimeBodyPart = addContent(mailEntity);
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);

            message.setRecipients(Message.RecipientType.CC,to.getAddress());
            //发送邮件
            Transport.send(message);
            System.out.println("发送成功....");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *添加附件
     * @param fileName
     * @return
     */
    public Multipart addAttch(String fileName){
        Multipart multipart = null;
        try{
            /**
             * Multipart 用于实现附件添加的组件
             */
            multipart = new MimeMultipart();
            BodyPart bodyPart = new MimeBodyPart();
            FileDataSource fileId = new FileDataSource(fileName);
            bodyPart.setDataHandler(new DataHandler(fileId));
            // 发送的附件前加上一个用户名的前缀
            bodyPart.setFileName(fileName);
            //添加附件
            multipart.addBodyPart(bodyPart);
        }catch (Exception e){
            e.printStackTrace();
        }
        return multipart;
    }

    /**
     * 设置附件内容
     * @param mailEntity
     * @return
     */
    public MimeBodyPart addContent(MailEntity mailEntity){
        /**
         * 文本组件
         */
        MimeBodyPart mimeBodyPart = null;
        try {
            mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailEntity.getContent(),"text/html;charset=utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mimeBodyPart;
    }
}