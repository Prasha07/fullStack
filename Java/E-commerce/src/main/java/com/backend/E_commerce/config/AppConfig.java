package com.backend.E_commerce.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

//Configuration is usd to create the beans uaing @Bean annotation
@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) -> this tells browser to not store any info and instad each timr authentication details will be sent in header in header for each requst(as we are using jwt)\
        http.sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                below line means anything comes with api/* authenticate it and give access to public tio use any other request other thaan api/*
                .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()).
//"Before Spring does its normal basic authentication checks, I want to run my own custom filter (JwtValidator) to check if the JWT token in the request is valid."
//        If valid it eill send the request to respective controller or else throws exception.
                addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
//                disable the csrf
                .csrf(csrf -> csrf.disable())
//                cors configuration to allow requests from frontend angular
                .cors(cors->cors.configurationSource(new CorsConfigurationSource() {


                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cfg = new CorsConfiguration();
//                        allow all request from this url
                        cfg.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//                        It allows all the methods like get ,post, put ,delete etc.
                        cfg.setAllowedMethods(Collections.singletonList("*"));

//                        I allow users to bring their ID card or token when talking to the server.”
                        cfg.setAllowCredentials(true);
//                        Frontend can send any kind of request headers
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
//                        Yes, frontend is allowed to see the Authorization token sent by the backend.(yhi se aayega token)
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                        keeps it valid for an hour
                        cfg.setMaxAge(3600L);
                        return cfg;
                    }
                }))
//                When you try to access a protected API (like /api/user), the browser will pop up a login prompt asking for a username and password.
//
//The credentials are sent with each request in the HTTP headers (base64-encoded).
                .httpBasic(Customizer.withDefaults())
//        When a user tries to access a protected resource, they are redirected to this form (/login).
//        After successful login, they are redirected to the originally requested page.
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
    @Bean
//    It is used to encrypt the password and then saves in DB, still we can match the encoded password and original password if required using
//            passwordEncoder.matches(rawPassword, hashedPasswordFromDB)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
