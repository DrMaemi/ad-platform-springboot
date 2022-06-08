package com.ssg.backendpreassignment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class TransactionConfig {
    private final DataSource dataSource;

    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public ResourcelessTransactionManager resourcelessTransactionManager() {
        return new ResourcelessTransactionManager();
    }
}
