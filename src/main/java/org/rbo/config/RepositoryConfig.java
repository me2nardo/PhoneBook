package org.rbo.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import javax.sql.DataSource;

/**
 * @author vitalii.levash
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.rbo.model"})
@EnableJpaRepositories(basePackages = {"org.rbo.dao"})
@EnableTransactionManagement
public class RepositoryConfig {


    /**
     * Creates and configures the HikariCP datasource bean.
     *
     * @param env The runtime environment of  our application.
     * @return
     */
    @Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {

        HikariConfig dataSourceConfig = new HikariConfig();

        dataSourceConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSourceConfig.setJdbcUrl(dataSourceProperties.getUrl());
        dataSourceConfig.setUsername(dataSourceProperties.getUsername());
        dataSourceConfig.setPassword(dataSourceProperties.getPassword());

        return new HikariDataSource(dataSourceConfig);

    }

    /**
     * Creates the bean that creates the JPA entity manager factory.
     *
     * @param dataSource The datasource that provides the database connections.
     * @param env        The runtime environment of  our application.
     * @return
     */

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env, RepositoryContext repositoryContext) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("org.rbo.model");
        entityManagerFactoryBean.setPersistenceUnitName(repositoryContext.getPersistenceUnitName());

        entityManagerFactoryBean.setJpaProperties(repositoryContext.getProperties());

        return entityManagerFactoryBean;
    }

    /**
     * Creates the transaction manager bean that integrates the used JPA provider with the
     * Spring transaction mechanism.
     *
     * @param entityManagerFactory The used JPA entity manager factory.
     * @return
     */
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}