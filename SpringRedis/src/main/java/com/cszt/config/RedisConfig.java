///**
// * Copyright (C), 2015-2018, XXX有限公司
// * FileName: RedisConfig
// * Author:   jj
// * Date:     2018/8/16 16:38
// * Description: redis配置
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.cszt.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈redis配置〉
// *
// * @author jj
// * @create 2018/8/16
// * @since 1.0.0
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate){
//        return new RedisCacheManager(redisTemplate);
//    }
//}