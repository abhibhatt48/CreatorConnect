package com.example.creatorconnectbackend.auth;

import java.io.IOException;
import java.util.ArrayList;

// Spring framework imports for Dependency Injection, Security Context, Authentication etc.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// Logger imports for logging relevant information and errors.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// JWT exception import for handling JWT related issues.
import io.jsonwebtoken.ExpiredJwtException;

// Servlet imports for handling request, response, and filter chain.
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Mark the class as a Spring Component.
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // Instance of JwtUtil for JWT related operations.
    private final JwtUtil jwtUtil;

    // Logger instance for this class.
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    // Constructor-based Dependency Injection of JwtUtil.
    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // This method is called for every request to check for the presence of JWT token.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Fetch the Authorization header from the incoming request.
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // Check if the Authorization header is valid and begins with "Bearer ".
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            // Extract the token by removing the "Bearer " prefix.
            jwtToken = requestTokenHeader.substring(7);
            try {
                // Extract username (in this case, email) from the token.
                username = jwtUtil.extractEmail(jwtToken);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                LOGGER.error("JWT Token has expired");
            }
        } else {
            LOGGER.warn("JWT Token does not begin with Bearer String");
        }

        // If the username was successfully extracted and the user is not yet authenticated.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Validate the extracted token.
            if (jwtUtil.validateToken(jwtToken, username)) {
                // If valid, set up the authentication object in the security context.
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Continue with the filter chain.
        chain.doFilter(request, response);
    }
}
