/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AuthenticatorImpl
 * Author:   jj
 * Date:     2018/8/13 12:59
 * Description: Authenticator实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.util;

import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;

/**
 * 〈一句话功能简述〉<br>
 * 〈Authenticator实现〉
 *
 * @author jj
 * @create 2018/8/13
 * @since 1.0.0
 */
public class AuthenticatorImpl extends Authenticator {
    private String userName;
    private String passWord;
    public AuthenticatorImpl(String userName,String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
    @Override
    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName,passWord);
    }
}