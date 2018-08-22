/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataSourcestest2
 * Author:   jj
 * Date:     2018/8/21 10:47
 * Description: test2数据源配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.test2.datasources;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.cszt.test2.config.DataConfigTest2;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈test2数据源配置〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = {"com.cszt.test2.mapper"}, sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSourcesTest2 {

    @Bean(name="test2DataSource")
    public DataSource testDatasource(DataConfigTest2 config2) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config2.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config2.getPassword());
        mysqlXADataSource.setUser(config2.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test2Datasource");

        atomikosDataSourceBean.setMinPoolSize(config2.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config2.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config2.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config2.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config2.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config2.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config2.getMaxIdleTime());
        return atomikosDataSourceBean;
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.cszt.domain");
        Resource resources = new PathMatchingResourcePatternResolver().getResource("classpath:UserMappertest2.xml");
        factoryBean.setMapperLocations(new Resource[]{resources});
//        //配置文件路径
//        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatisConfig.xml"));
        return factoryBean.getObject();
    }

    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}