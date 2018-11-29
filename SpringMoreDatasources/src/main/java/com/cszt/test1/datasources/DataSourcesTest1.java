/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataSourcesTest1
 * Author:   jj
 * Date:     2018/8/21 10:47
 * Description: test1数据源配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.test1.datasources;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 〈一句话功能简述〉<br>
 * 〈test1数据源配置〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = {"com.cszt.test1.repository"}, sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourcesTest1 {
    @Primary
    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "test1TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "test1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.cszt.domain");
        Resource resources = new PathMatchingResourcePatternResolver().getResource("classpath:UserMapperTest1.xml");
        factoryBean.setMapperLocations(new Resource[]{resources});
//        //配置文件路径
//        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatisConfig.xml"));
        return factoryBean.getObject();
    }
    @Primary
    @Bean(name="test1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}