package com.utc.api.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.utc.api.constants.Constant.ROLE_ADMIN;
import static com.utc.api.constants.Constant.ROLE_USER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                                                   // PUBLIC
                                                   .requestMatchers(
                                                       "/public",
                                                       "/auth/register",
                                                       "/auth/login",
                                                       "/auth/logout"
                                                   )
                                                   .permitAll()

                                                   //
                                                   .requestMatchers(
                                                       "/**",
                                                       "/auth/change-password"
                                                   )
                                                   .hasAnyRole(ROLE_USER, ROLE_ADMIN)

                                                   // ADMIN
                                                   .requestMatchers(
                                                       "/admin"
                                                   )
                                                   .hasRole(ROLE_ADMIN)

                                                   // USER
                                                   .requestMatchers(
                                                        "/user"
                                                   )
                                                   .hasRole(ROLE_USER)

                                                   .anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
