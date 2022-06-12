package com.ssg.backendpreassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 스프링 시큐리티의 기능을 정의하는 구성 클래스
 * mvcMatchers와 antMatchers를 이용해 스프링 시큐리티 필터 체인에서 일부 URL 접근 정책 제어
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .mvcMatchers("/ads", "/docs/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
