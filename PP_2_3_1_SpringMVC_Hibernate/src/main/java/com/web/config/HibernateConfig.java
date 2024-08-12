package com.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.PersistenceContext;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan("com.web")
@PropertySource("classpath:db.properties")
public class HibernateConfig {


    private final Environment env; // Для того, чтобы получить свойства из property файла.

    public HibernateConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean en= new LocalContainerEntityManagerFactoryBean();
        en.setDataSource(getDataSource());
        en.setPackagesToScan("com.web.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        en.setJpaVendorAdapter(vendorAdapter);
        en.setJpaProperties(hibernateProperties());
        return en;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager() {
        JpaTransactionManager manager=new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("db.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("db.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("db.dialect"));

        return properties;
    }

}
