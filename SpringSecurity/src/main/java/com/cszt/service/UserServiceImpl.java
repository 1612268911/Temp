/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   jj
 * Date:     2018/8/14 16:55
 * Description: 实现UserDetailsService接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.service;

import com.cszt.domian.Roles;
import com.cszt.mapper.UserJpa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import com.cszt.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈实现UserDetailsService接口〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserJpa userJpa;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("userName = " + userName);
		User user = userJpa.findUserByUserName(userName);
		if(user==null){
			return null;
		}
		/*List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(user.getRoles()));*/
		return new User(user.getUsername(),user.getPassword(),user.getRoles());
    }
}