package com.cszt.controller;

import com.cszt.domain.TokenResult;
import com.cszt.domain.User;
import com.cszt.service.TokenService;
import com.cszt.service.UserService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author lilin
 * @create 2018/8/28 16:56
 * Description: token控制器
 */
@Slf4j
@RestController
@SessionAttributes(types = {User.class})
public class TokenController {

    private static final String SECRET = "DyoonSecret_0581";

    private static final long INVAIL_DATE = 7200000L;

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    /**
     * @Author lilin
     * @Description
     * tokenDBEntity != null -> 验证是否超时 ->
     * 不超时 -> 直接返回dbToken
     * 超时 -> 生成newToken -> 更新dbToken -> 更新内存Token -> 返回newToken
     * @Date: 2018/8/28 21:17
     * @param:
     * @return:
     */
    @PostMapping("/token")
    public Map<String,Object> token(String userName,String passWord, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //userName is null
        if(StringUtils.isEmpty(userName))
        {
            map.put("error","userName为空！");
            return map;
        }
        //passWord is null
        if (StringUtils.isEmpty(passWord)){
            map.put("error","password为空！");
            return map;
        }
        //根据appId查询用户实体
        User user = userService.getUser(userName,passWord);
        request.getSession().setAttribute("user",user);
        //如果不存在
        if (user == null) {
            map.put("error","用户名或密码错误！");
            return map;
        }
        //检测数据库是否存在该appId的token值
        TokenResult token = tokenService.getToken(user.getUserId());
        //返回token值
        //tokenDBEntity == null -> 生成newToken -> 保存数据库 -> 写入内存 -> 返回newToken
        if(token == null) {
            //生成jwt,Token
            String tokenStr = createToken();
            TokenResult tokenResult = new TokenResult();
            tokenResult.setUserId(user.getUserId());
            tokenResult.setToken(tokenStr);
            tokenResult.setInvailDate(INVAIL_DATE);
            String tokenId = UUID.randomUUID().toString();
            tokenResult.setTokenId(tokenId);
            //将token保持到数据库
            tokenService.saveToken(tokenResult);
            map.put("success",tokenResult.getToken());
            return map;
        }
        //判断数据库中token是否过期，如果没有过期不需要更新直接返回数据库中的token即可
        //数据库中生成时间
        long dbBuildTime = token.getInvailDate();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //如果当前时间 - 数据库中生成时间 < 7200 证明可以正常使用
        long second = TimeUnit.MILLISECONDS.toSeconds(currentTime - dbBuildTime);
        if (second > 0 && second < INVAIL_DATE) {
            String tokenStr = token.getToken();
            map.put("success",token.getToken());
            return map;
            //超时
        }else{
            //生成newToken
            String tokenStr = createToken();
            //更新token
            token.setToken(tokenStr);
            //执行更新
            tokenService.updateToken(token);
            map.put("success",token.getToken());
            return map;
        }
    }
    /**
     * @Author lilin
     * @Description 创建token
     * @Date: 2018/8/28 17:20
     * @param:
     * @return:
     */
    @GetMapping("/createToken")
    public String createToken(){
        long currDate = System.currentTimeMillis()+INVAIL_DATE;
        Date invailDate = new Date(currDate);
        Claims claims = Jwts.claims();
        claims.put("name","yangwenyao");
        claims.put("age","43");
        claims.setAudience("cy");
        claims.setIssuer("cy");
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(invailDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        return token;
    }
    /**
     * @Author lilin
     * @Description 解析token
     * @Date: 2018/8/28 17:20
     * @param:
     * @return:
     */
    @GetMapping("/parseToken")
    public Map<String,Object> parseToken(String token){
        Jws<Claims> jws =  Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        String signature = jws.getSignature();
        log.info("signature-->{}",signature);
        Map<String,String> headerMap = jws.getHeader();
        log.info("headr-->{}",headerMap);
        Claims claims = jws.getBody();
        Map<String,Object> bodyMap = new HashMap<>();
        Set<Map.Entry<String,Object>> set = claims.entrySet();
        set.forEach(entry->{
            bodyMap.put(entry.getKey(),entry.getValue());
        });
        log.info("body-->{}",bodyMap);
        return bodyMap;
    }
    @GetMapping("/home")
    public String home(){
        return "success";
    }
}