/**
 * -----------------------------------------------------------------------------
 *                           JWT Request Filter
 * -----------------------------------------------------------------------------
 * Purpose: 
 * This class is responsible for filtering and processing JWT tokens present 
 * in incoming requests. It belongs to the 'com.example.creatorconnectbackend.auth' package.
 * The filter validates the JWT, extracts the username (in this case, an email), 
 * and sets the user's authentication in the security context.
 *
 * Main Components:
 * - JwtRequestFilter: A class that extends the OncePerRequestFilter, ensuring the
 *   doFilterInternal method is executed once per request. It handles the JWT extraction,
 *   validation, and user authentication.
 * - LOGGER: A static logger instance for logging exceptions and warnings.
 *
 * Main Functions:
 * - doFilterInternal(): Called for each request. This method checks for the presence of 
 *   a JWT token in the request header, validates it, and sets the user's authentication 
 *   in the security context. If the token is invalid or expired, appropriate logging 
 *   messages are captured.
 *
 * Dependencies:
 * - JwtUtil: A utility class (not provided) presumably responsible for JWT operations such 
 *   as token validation and extracting the email from the token.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.auth;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Annotates the class as a Spring Component.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    /**
     * Constructor-based Dependency Injection of JwtUtil.
     * 
     * @param jwtUtil The instance of JwtUtil used for JWT related operations.
     */
    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * This method is called for every request to check for the presence of JWT token
     * and process it accordingly.
     *
     * @param request  The HTTP servlet request.
     * @param response The HTTP servlet response.
     * @param chain    The filter chain.
     * @throws ServletException If there is a servlet exception.
     * @throws IOException      If there is an input-output exception.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.extractEmail(jwtToken);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                LOGGER.error("JWT Token has expired");
            }
        } else {
            LOGGER.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwtToken, username)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
