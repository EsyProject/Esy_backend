package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.email.DataDetailEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import apiBoschEsy.apiInSpringBoot.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<DataDetailEmail> registerEmail(@RequestBody @Valid DataRegisterEmail dataRegisterEmail, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Jwt jwt){
        var email = emailService.sendingEmail(jwt, dataRegisterEmail);
        var uri = uriBuilder.path("/email/{email_id}").build(email.email_id());
        return ResponseEntity.created(uri).body(email);
    }
}
