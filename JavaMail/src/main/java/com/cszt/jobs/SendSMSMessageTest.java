/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SendSMSMessageTest
 * Author:   jj
 * Date:     2018/8/14 9:29
 * Description: 发送sms短信测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.jobs;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈发送sms短信测试〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
public class SendSMSMessageTest {
    /**
     * 通过SMS平台发送短信
     * @param content 短信内容
     * @param phoneNumber 手机号
     */
    public static void sendSmsMessage(String content,String phoneNumber){
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        // PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", "a1234567890"),// 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"),// 注册成功后，登录网站后得到的密钥
                new NameValuePair("smsMob", phoneNumber),// 手机号码
                new NameValuePair("smsText", content) };// 短信内容
        post.setRequestBody(data);
        try {
            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:" + statusCode);
            for (Header h : headers) {
                System.out.println("h.toString-->" + h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
            System.out.println("result=="+result);
            System.out.println("发送成功...");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        String content = "【有钱花】兄弟，该还钱了！";
        String phoneNumber = "18229773049";
        sendSmsMessage(content,phoneNumber);
    }
}