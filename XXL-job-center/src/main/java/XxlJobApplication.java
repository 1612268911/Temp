/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: XxlJobApplication
 * Author:   jj
 * Date:     2018/8/9 15:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/9
 * @since 1.0.0
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"controller"})
@MapperScan(basePackages = {"mapper"})
public class XxlJobApplication {
    public static void main(String[] args){
        SpringApplication.run(XxlJobApplication.class,args);
    }
}