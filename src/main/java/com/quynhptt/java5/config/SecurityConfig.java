package com.quynhptt.java5.config;

import com.quynhptt.java5.constant.RoleConstants;
import com.quynhptt.java5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole(RoleConstants.ADMIN)
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                ).userDetailsService(userService)
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .formLogin(login -> login
                        .defaultSuccessUrl("/loginSuccess", true)
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .exceptionHandling(ex ->
                        ex.accessDeniedPage("/login?error=true")
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
