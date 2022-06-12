package com.ssg.backendpreassignment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

/**
 * 트랜잭션 매니저에 대한 스프링 빈 정책을 정의하는 구성 클래스
 * 스프링 프레임워크에서 사용하는 트랜잭션 매니저와 스프링 배치에서 사용하는 트랜잭션 매니저 우선순위 정의
 */
@Configuration
@RequiredArgsConstructor
public class TransactionConfig {
    private final DataSource dataSource;

    /**
     * application.yml에서 작성한 spring.datasource 객체를 주입받아 JPA 트랜잭션 매니저 빈 객체 등록
     * @return JpaTransactionManager
     */
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
