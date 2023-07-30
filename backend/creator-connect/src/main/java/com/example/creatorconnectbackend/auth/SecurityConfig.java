package com.example.creatorconnectbackend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// This class is a Configuration class, which means it can provide bean definitions to the Spring IoC container.
@Configuration
// This annotation enables Spring's web security features.
@EnableWebSecurity
public class SecurityConfig {

    // This is a reference to our JWT request filter that will be used to authenticate requests with JWT.
    private final JwtRequestFilter jwtRequestFilter;

    // Constructor-based dependency injection of the JwtRequestFilter.
    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // This method is defining the security filter chain for our application using the provided HttpSecurity object.
    @Bean	
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (Cross-Site Request Forgery) protections as JWT is immune to CSRF.
            .csrf().disable()

            // Define which requests should be secured.
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    // Allow anyone to access registration and login endpoints.
                    .requestMatchers("/api/users/register", "/api/users/login").permitAll()

                    // Any other request should be authenticated.
                    .anyRequest().authenticated()
            )

            // Handle exceptions (like unauthorized requests).
            .exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            )

            // Define the session management policy. Here, it's stateless meaning no session will be created.
            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        // Add our JWT filter before the default UsernamePasswordAuthenticationFilter.
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // Return the built security filter chain.
        return http.build();
    }
}
