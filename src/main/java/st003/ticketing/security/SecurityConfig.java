package st003.ticketing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import st003.ticketing.data.repositories.AppUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AppUserRepository repo;

    public SecurityConfig(AppUserRepository repo) {
        this.repo = repo;
    }

    /**
     * Returns an instance of the AppUserDetailsService used for authenticating
     * with Spring Security against an AppUser.
     *
     * @return A new AppUserDetailsService object
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService(repo);
    }

    /**
     * Returns an instance of the BCryptPasswordEncoder used for hashing the
     * submitted plaintext password before comparing to stored hash.
     *
     * @return A new BCryptPasswordEncoder object
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Returns an instance of the AuthenticationSuccessHandler used for redirecting
     * an authenticated user to the correct page after login based on their role.
     *
     * @return A new loginSuccessHandler object
     */
    @Bean
    public AuthenticationSuccessHandler getLoginSuccessHandler() {
        return new loginSuccessHandler();
    }

    @Bean
    public SecurityFilterChain defauFilterChain(HttpSecurity http) throws Exception {

        // TODO - TEMPORARY
        // (1) h2-console configs included only for testing
        // (2) header frame options only disabled for h2-console

        http
            .authorizeHttpRequests(authorizeHttpRequestCustomizer -> authorizeHttpRequestCustomizer
                .requestMatchers("/", "/register", "/h2-console/**").permitAll()
                .requestMatchers("/ticket", "/tickets", "/new-ticket").hasRole("CUSTOMER")
                .requestMatchers("/agent/**").hasAnyRole("AGENT", "ADMIN")
                .anyRequest().authenticated()

            ).formLogin(formLoginCustomizer -> formLoginCustomizer
                .loginPage("/login")
                .permitAll()
                // customize login redirect based on AppUser role
                .successHandler(getLoginSuccessHandler())

            ).logout(logoutCustomizer -> logoutCustomizer
                .logoutSuccessUrl("/")

            ).csrf(csrfCustomizer -> csrfCustomizer
                .ignoringRequestMatchers("/h2-console/**")

            ).headers(headersCustomizer -> headersCustomizer
                .frameOptions(frames -> frames.disable())
            );

        return http.build();
    }
}
