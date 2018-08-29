package com.cszt.service;

import com.cszt.domain.TokenResult;
import com.cszt.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lilin
 * @create 2018/8/28 20:31
 * Description:
 */
@Service
public class TokenService {
    @Autowired
    private TokenMapper tokenMapper;

    public TokenResult getToken(Integer userId){
        return tokenMapper.getToken(userId);
    }
    public int saveToken(TokenResult tokenResult){
        return tokenMapper.saveToken(tokenResult);
    }
    public int updateToken(TokenResult tokenResult){
        return tokenMapper.updateToken(tokenResult);
    }
}