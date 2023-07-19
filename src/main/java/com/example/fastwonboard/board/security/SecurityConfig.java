package com.example.fastwonboard.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.userDetailsService(userDetailsService);

        security.authorizeHttpRequests(request -> request.requestMatchers("/*", "/system/**").permitAll()
                .requestMatchers("/board/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/comment/**").authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true))
                .exceptionHandling(eH -> eH.accessDeniedPage("/system/accessDenied"))
                .logout(logout -> logout.logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/"));

        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
