package com.st003.ticketing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.st003.ticketing.data.repositories.AppUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AppUserRepository repo;

    public SecurityConfig(AppUserRepository repo) {
        this.repo = repo;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService(repo);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defauFilterChain(HttpSecurity http) throws Exception {

        // TODO - TEMPORARY
        // (1) h2-console configs included only for testing
        // (2) header frame options only disabled for h2-console

        http
            .authorizeHttpRequests(authorizeHttpRequestCustomizer -> authorizeHttpRequestCustomizer
                .requestMatchers("/", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            ).formLogin(formLoginCustomizer -> formLoginCustomizer
                .loginPage("/login").permitAll()
            ).csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            ).headers(headers -> headers
                .frameOptions(frames -> frames.disable())
            );

        return http.build();
    }
}
