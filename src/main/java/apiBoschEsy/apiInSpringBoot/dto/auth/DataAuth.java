package apiBoschEsy.apiInSpringBoot.dto.auth;

import org.springframework.security.oauth2.jwt.Jwt;

public record DataAuth(
        String userName,
        String nameNetworkUser
) {
    public DataAuth(Jwt jwt){
        this(
                jwt.getClaimAsString("name"),
                jwt.getClaimAsString("preferred_username")
        );
    }
}
