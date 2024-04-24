package apiBoschEsy.apiInSpringBoot.infra.security;

import apiBoschEsy.apiInSpringBoot.repository.IRepositoryUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// Creates a filter for battery request (The component is used for the String to load a generic class/component)
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private IRepositoryUser repositoryUser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // You need to call other filters to hit the controller (By going through the Handler Interceptors filter)
        // FilterChain -> Filter chain

        // Method for getToken
        var tokenJWT = getToken(request);
        // GetUser
        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var user = repositoryUser.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Auth the user


        filterChain.doFilter(request, response); // Continue the request flow
    }

    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
