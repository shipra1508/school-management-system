package com.school.courseservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.school.courseservice.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Only ADMIN can create courses
                .requestMatchers(HttpMethod.POST, "/courses").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/courses/enrollment-details/**").hasAnyRole("ADMIN","TEACHER")
                .requestMatchers(HttpMethod.POST, "/courses/student-enroll").hasAnyRole("ADMIN","TEACHER","STUDENT")
                .requestMatchers(HttpMethod.GET, "/courses/listAllCourses").hasAnyRole("ADMIN","TEACHER","STUDENT")
                .requestMatchers(HttpMethod.GET, "/courses/findByCode/**").hasAnyRole("ADMIN","TEACHER","STUDENT")
                .requestMatchers(HttpMethod.GET, "/courses/findByName/**").hasAnyRole("ADMIN","TEACHER","STUDENT")
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
