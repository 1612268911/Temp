package com.szzt.smart.testlist;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 多个表关联，resultMap处理 测试
 *
 * 加载自定义配置文件
 *
 * 分页插件MyBatisPlugin的使用
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.szzt.smart.testlist"})
@MapperScan(basePackages = {"com.szzt.smart.testlist.mapper"})
public class Testlist
{
	public static void main(String[] args)
	{
		SpringApplication.run(Testlist.class, args);
	}
}
