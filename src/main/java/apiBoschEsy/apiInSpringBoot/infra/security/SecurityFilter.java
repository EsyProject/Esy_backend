package apiBoschEsy.apiInSpringBoot.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Cria um filtro para bater requisição (O componente é utilizado para que o String carregue uma classe/componente genérico)
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Você precisa chamar outros filtros para conseguir bater no controoler (Passando pelo filtro Handler Interceptors)
        // FilterChain -> Cadeia de filtros
        filterChain.doFilter(request, response); // Continua o fluxo da requisição


    }
}
