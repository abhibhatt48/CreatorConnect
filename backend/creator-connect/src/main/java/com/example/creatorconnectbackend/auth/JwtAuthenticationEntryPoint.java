// Define the package to which this class belongs.
package com.example.creatorconnectbackend.auth;

// Import necessary classes for handling authentication exceptions and defining entry points.
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

// Import the Spring `Component` annotation, which marks this class as a Spring component.
import org.springframework.stereotype.Component;

// Import necessary classes for handling HTTP servlet requests and responses.
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException; // Import IOException for handling potential input-output exceptions.

// Annotate the class with `@Component` so that Spring recognizes it as a component
// and manages its lifecycle.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Override the `commence` method of the `AuthenticationEntryPoint` interface.
    // This method is called whenever an AuthenticationException is thrown by the 
    // protected endpoint, meaning that the user is not authenticated.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // If the user is not authenticated, send an HTTP 401 (Unauthorized) response.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
