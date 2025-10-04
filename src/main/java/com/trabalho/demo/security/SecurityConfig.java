package com.trabalho.demo.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/blog-matematica.html",
                                "/blog-biologia.html",
                                "/login"
                        ).permitAll()
                        // libera todos arquivos estáticos em common locations
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // libera explicitamente CSS e JS
                        .requestMatchers("/**/*.css", "/**/*.js").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.disable()) // desabilita a segurança de headers
                .formLogin(form -> form.disable());

        return http.build();
    }

}
