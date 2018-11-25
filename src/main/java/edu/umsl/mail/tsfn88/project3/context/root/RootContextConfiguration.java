/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Configuration for the root context.
 */
@Configuration
@EnableJpaRepositories(basePackages = "edu.umsl.mail.tsfn88.repository")
@EnableTransactionManagement
public class RootContextConfiguration {

    private static final Logger log = LogManager.getLogger();

    /**
     * The database URL.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/enterprise";

    /**
     * The database username.
     */
    private static final String DB_USERNAME = "mysql";

    /**
     * The database password.
     */
    private static final String DB_PASSWORD = "password";

    @Bean
    public DataSource dataSource() {
        log.trace("dataSource");

        // Create JDBC data source for MySQL database
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl(DB_URL);
        source.setUsername(DB_USERNAME);
        source.setPassword(DB_PASSWORD);
        return source;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        log.trace("jpaVendorAdapter");

        // Create JPA adapter for using the MySQL InnoDB engine with Hibernate
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLInnoDBDialect");
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        log.trace("entityManagerFactory");

        // Create entity manager factory with above configuration for our entities
        // The data source and vendor adapter beans are autowired into this function
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("edu.umsl.group1.entity");
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        log.trace("transactionManager");

        // Create transaction manager for entity manager factory
        // The entity manager factory bean is autowired in
        return new JpaTransactionManager(factory);
    }

}
