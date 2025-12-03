package com.school.attendance.config;

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
import org.springframework.web.client.RestTemplate;

import com.school.attendance.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						// Public endpoints
						.requestMatchers("/auth/**").permitAll()

						//endpoints protected by roles
						.requestMatchers(HttpMethod.POST, "/attendance/mark/**").hasAnyRole("TEACHER", "ADMIN")
						.requestMatchers(HttpMethod.GET, "/attendance/student/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
						.requestMatchers(HttpMethod.GET, "/attendance/subject/**").hasAnyRole("TEACHER", "ADMIN")

						// Swagger and actuator open
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/actuator/**").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
