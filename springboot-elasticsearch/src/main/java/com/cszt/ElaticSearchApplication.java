package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/***
 * elasticsearch,springboot
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"com.cszt.repository"})
public class ElaticSearchApplication{
    public static void main(String[] args){
        SpringApplication.run(ElaticSearchApplication.class,args);
    }
}