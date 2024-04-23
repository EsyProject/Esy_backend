package apiBoschEsy.apiInSpringBoot.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// Creates a filter for battery request (The component is used for the String to load a generic class/component)
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // You need to call other filters to hit the controller (By going through the Handler Interceptors filter)
        // FilterChain -> Filter chain

        // Method for getToken
        var tokenJWT = getToken(request);

        var subject = tokenService.getSubject(tokenJWT);
        System.out.println(subject);

        filterChain.doFilter(request, response); // Continue the request flow
    }

    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){
            throw new RuntimeException("Token JWT not send in Authorization Header!");
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}
