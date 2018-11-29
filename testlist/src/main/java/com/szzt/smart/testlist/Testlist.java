package com.szzt.smart.testlist;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;


/**
 * 多个表关联，resultMap处理 测试
 *
 * 加载自定义配置文件
 *
 * 分页插件MyBatisPlugin的使用
 *
 * logback.xml打印日志
 *
 * lombok简化代码
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.szzt.smart.testlist"})
@MapperScan(basePackages = {"com.szzt.smart.testlist.repository"})
public class Testlist
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(Testlist.class).web(true).run(args);
	}
}
