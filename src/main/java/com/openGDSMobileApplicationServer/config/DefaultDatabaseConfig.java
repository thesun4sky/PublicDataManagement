package com.openGDSMobileApplicationServer.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;


/**
 * Created by intruder on 16. 8. 2.
 */
@Configuration
@Lazy
@EnableTransactionManagement
@MapperScan(
        basePackages = "com.openGDSMobileApplicationServer.mapper",
        sqlSessionFactoryRef = "MyBatis_PostgreSQL_SqlSessionFactory",
        sqlSessionTemplateRef = "MyBatis_PostgreSQL_SqlSessionTemplate")
/*@MapperScan(value={"com.openGDSMobileApplicationServer.mapper"})*/
public class DefaultDatabaseConfig{

    @Autowired
    private ApplicationContext applicationContext;


    @Bean(name = "MyBatis_PostgreSQL_DataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() throws IOException
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "MyBatis_PostgreSQL_SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.openGDSMobileApplicationServer.valueObject");
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "MyBatis_PostgreSQL_SqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate() throws Exception
    {
        return new SqlSessionTemplate(getSqlSessionFactory());
    }



/*

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
*/

}
