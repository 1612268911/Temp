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

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.cszt.test1.config.DataConfigTest1;
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
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * 〈一句话功能简述〉<br>
 * 〈test1数据源配置〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = {"com.cszt.test1.mapper"}, sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourcesTest1 {
    @Primary
    @Bean(name="test1DataSource")
    public DataSource testDatasource(DataConfigTest1 config1) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config1.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config1.getPassword());
        mysqlXADataSource.setUser(config1.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test1Datasource");

        atomikosDataSourceBean.setMinPoolSize(config1.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config1.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config1.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config1.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config1.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config1.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config1.getMaxIdleTime());
        return atomikosDataSourceBean;
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