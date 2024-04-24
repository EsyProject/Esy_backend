package apiBoschEsy.apiInSpringBoot.infra.security;

import apiBoschEsy.apiInSpringBoot.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") //Makes Spring relate this variable to the application.yaml file variable
    private String secret;
    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Esy Bosch")
                    .withSubject(user.getLogin()) // Role relacioned with Token
                    .withExpiresAt(dateExpire())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error a generator token JWT", exception);
        }
    }

    // Method for getSubject
    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Esy Bosch")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT token!");
        }
    }

    private Instant dateExpire(){
        // Get date current, plus two hours, and convert for Instant (Fuso de 3 (TimeZone))
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
