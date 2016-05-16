package com.sf.kycmanager.base.dbconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Cyprian Omenuko on 4/12/16.
 */
@EnableTransactionManagement //(mode = AdviceMode.ASPECTJ)
@EnableJpaRepositories(basePackages = {"com.sf.kycmanager.portlet.repository"})
@ComponentScan(basePackages = {"com.sf.kycmanager.portlet.service"})
@PropertySource("classpath:application.properties")
public class DataBaseConfig {

    @Autowired
    public DataSource dataSource;
    @Autowired
    public EntityManagerFactory entityManagerFactory;
    @Value("${jndi.name}")
    private String JNDI_NAME;
    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;
    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;
    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;
    @Value("${entityManager.packagesToScan}")
    private String ENTITY_MANAGER_PACKAGES_TO_SCAN;


    public DataBaseConfig() {

    }

    @Bean
    public JndiObjectFactoryBean dataSource() {
        JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        dataSource.setJndiName(JNDI_NAME);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);

        // Classpath scanning of @Component, @Service, etc annotated class
        entityManagerFactory.setPackagesToScan(ENTITY_MANAGER_PACKAGES_TO_SCAN);

        // Vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        additionalProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        additionalProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
//        additionalProperties.put("hibernate.archive.autodetection","class, hbm");
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

    /**
     * Declare the transaction manager.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


} // class DatabaseConfig
