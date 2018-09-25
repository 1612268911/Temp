package com.ztdz; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: com.ztdz.HttpClientTest
 * Author:   jj
 * Date:     2018/7/18 20:13
 * Description: HttpClientC测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */



import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈HttpClientC测试〉
 *
 * @author jj
 * @create 2018/7/18
 * @since 1.0.0
 */
public class HttpClientTest {
    public static String SendHttpPost(String url, Object object){
        HttpClient httpClient = new DefaultHttpClient();
        //设置超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);
        HttpPost post = new HttpPost(url);
        //设置头部
        post.setHeader("Content-Type","application/json;charset=UTF-8");
        //设置消息体
        JSONObject jsonObject = JSONObject.fromObject(object);
        System.out.println("jsonObject-->"+jsonObject);
        StringEntity entity = new StringEntity(jsonObject.toString(), Charset.forName("UTF-8"));
        entity.setContentType("application/json");
        entity.setContentEncoding("UTF-8");

        post.setEntity(entity);

        HttpResponse response = null;
        String result = null;
        OutputStream out = null;
        try{
            response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
//            HttpEntity entity1 = response.getEntity();
//            InputStream in = entity1.getContent();
//            result = HttpClientTest.parseStream(in);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(post!=null){
                post.releaseConnection();
            }
        }
        return result;
    }
    public static String sendGet(String url,String param) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url+"?value="+param);//配成键值对
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //key-value
    public static String postKeyValue(String url, Map<String,String> paramsMap){
        String result = null;
        HttpPost post = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();

            post = new HttpPost(url);

            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();

            Set<Map.Entry<String,String>> set =  paramsMap.entrySet();
            for (Map.Entry<String, String> entry : set) {
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(
                        entry.getKey(), entry.getValue());
                parameters.add(basicNameValuePair);
            }

            UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
                    parameters);
            // 提交表单类型的参数
            post.setEntity(reqEntity);

            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity);
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }finally {
            if(post!=null){
                post.releaseConnection();
            }
        }
        return result;
    }
//        /**
//         * 解析输入流
//         * @param in
//         * @return
//         */
//    public static String parseStream(InputStream in){
//        BufferedReader bs = null;
//        try{
//            bs = new BufferedReader(new InputStreamReader(in));
//            String str = "";
//            String newStr = "";
//            while((str = bs.readLine())!=null){
//                newStr += str;
//            }
//            return newStr;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
    public static void main(String[] args){
//        domain user = new domain();
//        user.setName("老大");
//        user.setPwd("123456");
//
//        String obj = HttpClientTest.SendHttpPost("http://localhost:8080/test",user);
//        System.out.println(obj);

//        String obj = HttpClientTest.sendGet("http://localhost:8080/getTest","jj");
//        System.out.println("obj -> "+obj);

        Map<String,String> map = new HashMap<String, String>();
        map.put("value","jjj");
        String result = HttpClientTest.postKeyValue("http://localhost:8080/testValue",map);
        System.out.println(result);
    }
}