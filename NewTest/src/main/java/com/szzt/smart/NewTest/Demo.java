package com.szzt.smart.NewTest;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * apidoc生成测试
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
@ComponentScan({"com.szzt.smart", "com.szzt.smart.NewTest"})
@MapperScan(basePackages = {"mapper"})
public class Demo
{
	public static void main(String[] args)
	{
		SpringApplication.run(Demo.class, args);
	}
}
