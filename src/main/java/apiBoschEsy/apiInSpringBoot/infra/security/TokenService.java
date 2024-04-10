package apiBoschEsy.apiInSpringBoot.infra.security;

import apiBoschEsy.apiInSpringBoot.entity.User;
import com.auth0.jwt.JWT;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generateToken(User user){
        // Criando uma lógica para criação de Token
        try {
            var algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API Esy Bosch")
                    .withSubject(user.getLogin()) // Role relacioned with Token
                    .withExpiresAt(dateExpire())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error a generator token JWT", exception);
        }
    }

    private Instant dateExpire(){
        // Get date current, plus two hours, and convert for Instant (Fuso de 3 (TimeZone))
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
