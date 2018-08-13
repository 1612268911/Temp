/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JavaMailApplication
 * Author:   jj
 * Date:     2018/8/13 11:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import com.cszt.domain.MailSender;
import com.cszt.util.MailContentType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/13
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cszt"})
public class JavaMailApplication {
    public static void main(String[] args){
        SpringApplication.run(JavaMailApplication.class,args);
        sendMailTest();
    }
    public static void sendMailTest(){
        List<String> list = new ArrayList<String>();
        list.add("3035796278@qq.com");
        new MailSender()
                .setTitle("测试邮件")
                .setContent("测试邮件内容")
//                .setContenType(MailContentType.HTML.getContentType())
                .setContenType("text/html;charset=utf-8")
                .setList(list)
                .send();
    }
}