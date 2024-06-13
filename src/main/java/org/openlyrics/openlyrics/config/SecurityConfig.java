package org.openlyrics.openlyrics.config;

import org.openlyrics.openlyrics.filter.UserAuthenticatorFilter;
import org.openlyrics.openlyrics.model.enumeration.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserAuthenticatorFilter userAuthenticatorFilter;


    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
        "/api/auth/login",
        "/api/user",
        "/api/ping",
        "/api/pub"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {};

    public static final String[] ENDPOINTS_ADMIN = {};

    public static final String[] ENDPOINTS_EDITOR = {};

    public static final String[] ENDPOINTS_USER = {};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrfCustomizer -> csrfCustomizer.disable())
                    .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authorizeHttpRequests(authorizationCustomizer -> authorizationCustomizer
                    .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                    .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                    .requestMatchers(ENDPOINTS_EDITOR).hasAnyRole(RoleName.ADMIN.name(), RoleName.EDITOR.name())
                    .requestMatchers(ENDPOINTS_USER).hasAnyRole(RoleName.ADMIN.name(), RoleName.USER.name())
                    .anyRequest().denyAll()
                ).addFilterBefore(userAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}