package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.email.DataDetailEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/{event_id}")
    public ResponseEntity<DataDetailEmail> registerEmail(UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Jwt jwt, @PathVariable Long event_id, @RequestBody @Valid DataRegisterEmail dataRegisterEmail) throws EventNotFoundException {
        var email = emailService.sendingEmail(jwt, event_id, dataRegisterEmail);
        var uri = uriBuilder.path("/email/{email_id}").build(email.email_id());
        return ResponseEntity.created(uri).body(email);
    }
}