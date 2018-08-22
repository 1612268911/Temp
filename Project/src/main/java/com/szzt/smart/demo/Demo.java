package com.szzt.smart.demo;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.szzt.smart", "com.szzt.smart.demo"})
@MapperScan("com.szzt.smart.demo.com.cszt.mapper")
public class Demo
{
	public static void main(String[] args)
	{
		SpringApplication.run(Demo.class, args);
	}
}
