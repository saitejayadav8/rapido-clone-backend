package com.rapido.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager users(
            PasswordEncoder encoder) {

        UserDetails superAdmin =
                User.builder()
                        .username("superadmin")
                        .password(encoder.encode("admin123"))
                        .roles("SUPER_ADMIN")
                        .build();

        UserDetails financeAdmin =
                User.builder()
                        .username("finance")
                        .password(encoder.encode("finance123"))
                        .roles("FINANCE_ADMIN")
                        .build();

        UserDetails supportAdmin =
                User.builder()
                        .username("support")
                        .password(encoder.encode("support123"))
                        .roles("SUPPORT_ADMIN")
                        .build();

        UserDetails driver =
                User.builder()
                        .username("driver")
                        .password(encoder.encode("driver123"))
                        .roles("DRIVER")
                        .build();

        UserDetails user =
                User.builder()
                        .username("user")
                        .password(encoder.encode("user123"))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(
                superAdmin,
                financeAdmin,
                supportAdmin,
                driver,
                user
        );
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/actuator/**",
                                "/error",
                                "/login")
                        .permitAll()

                        .requestMatchers("/admin/**")
                        .hasRole("SUPER_ADMIN")

                        .requestMatchers("/finance/**")
                        .hasAnyRole(
                                "FINANCE_ADMIN",
                                "SUPER_ADMIN")

                        .requestMatchers("/support/**")
                        .hasAnyRole(
                                "SUPPORT_ADMIN",
                                "SUPER_ADMIN")

                        .requestMatchers("/driver/**")
                        .hasRole("DRIVER")

                        .requestMatchers("/user/**")
                        .hasRole("USER")

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}