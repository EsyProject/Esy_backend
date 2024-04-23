package apiBoschEsy.apiInSpringBoot.controller;


import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.auth.DataTokenJWT;
import apiBoschEsy.apiInSpringBoot.entity.User;
import apiBoschEsy.apiInSpringBoot.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    // AuthManager -> Runs our created service, under the hood
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid DataAuth data){
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password()); // This token does not recognize DTO, so it needs to pass the DTO that it understands
        var auth = manager.authenticate(authToken); // Represents the user

        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new DataTokenJWT(tokenJWT));
    }

}
