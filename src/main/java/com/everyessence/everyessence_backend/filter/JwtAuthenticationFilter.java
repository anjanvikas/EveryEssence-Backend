package com.everyessence.everyessence_backend.filter;

import com.everyessence.everyessence_backend.util.JWTTokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenProvider jwtProvider;

    public JwtAuthenticationFilter(JWTTokenProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Extract JWT token from Authorization header
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix

            try {
                // Validate the token and set authentication if it's valid
                if (jwtProvider.validateToken(token)) {
                    // Token is valid, user is authenticated
                    String emailString = jwtProvider.getEmailFromToken(token);

                    // Create an authentication object with the user details
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        emailString, 
                        null, // Credentials not required as we are using JWT
                        null
                    );
                    // Set authentication details (e.g., IP address, etc.)
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the authentication in the Spring Security context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // Handle token validation exception
                System.out.println("Invalid JWT token: " + e.getMessage());
            }
        }

        // Continue the request to the next filter or controller
        filterChain.doFilter(request, response);
    }
}