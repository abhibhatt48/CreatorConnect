/**
 * -----------------------------------------------------------------------------
 *                            Security Configuration
 * -----------------------------------------------------------------------------
 * Purpose: 
 * The 'SecurityConfig' class is a pivotal component of the 'com.example.creatorconnectbackend.auth' package,
 * providing configuration that initializes and tailors Spring's web security features. Additionally, it 
 * supplies bean definitions to the Spring IoC container for specific security components.
 *
 * Annotations:
 * - @Configuration: This class is a source of bean definitions for the Spring IoC container.
 * - @EnableWebSecurity: Activates Spring's web security features.
 *
 * Main Components:
 * - jwtRequestFilter: An instance of JwtRequestFilter used to filter and process JWT tokens in incoming requests.
 *
 * Key Functions:
 * - securityFilterChain(HttpSecurity http): This function outlines the application's security configurations.
 *   - CSRF protections are disabled, as JWT is inherently resistant to CSRF attacks.
 *   - Certain endpoints, like registration and login, are universally accessible.
 *   - All other endpoints mandate authentication.
 *   - Defines the behavior when authentication fails.
 *   - Configures session management to be stateless, consistent with JWT-based authentication.
 *   - The JwtRequestFilter is set up to operate before the standard UsernamePasswordAuthenticationFilter.
 *
 * Dependencies:
 * - JwtRequestFilter: A filter used for processing JWT tokens present in incoming requests.
 * - HttpSecurity: Used for configuring web-based security for specific http requests.
 * - JwtAuthenticationEntryPoint: A component that handles authentication exceptions.
 *
 * Notes:
 * It's essential to keep the configuration updated, especially the public endpoints, to ensure the application's
 * security remains uncompromised.
 * -----------------------------------------------------------------------------
 */

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

/**
 * Annotate the class as a Configuration class to provide bean definitions to the Spring IoC container.
 */
@Configuration
/**
 * Enable Spring's web security features.
 */
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Defines the security filter chain for the application using the provided HttpSecurity object.
     *
     * @param http The HttpSecurity object to configure the security filter chain.
     * @return The configured SecurityFilterChain object.
     * @throws Exception If an exception occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()

            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    // Allow anyone to access registration and login endpoints.
                    .requestMatchers(
                        "/api/users/register",
                        "/api/users/login"
                    ).permitAll()

                    // Any other request should be authenticated.
                    .anyRequest().authenticated()
            )

            .exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            )

            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
