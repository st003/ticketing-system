package com.st003.ticketing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defauFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorizeHttpRequestCustomizer -> authorizeHttpRequestCustomizer
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
            ).formLogin(Customizer.withDefaults());

        return http.build();
    }
}
