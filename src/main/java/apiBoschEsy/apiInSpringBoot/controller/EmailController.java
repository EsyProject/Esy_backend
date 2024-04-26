package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import apiBoschEsy.apiInSpringBoot.entity.Email;
import apiBoschEsy.apiInSpringBoot.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid DataRegisterEmail dataRegisterEmail, UriComponentsBuilder uriComponentsBuilder){
        var email = new Email();
        BeanUtils.copyProperties(dataRegisterEmail, email);
        emailService.sendEmail(email);
        // Return URI
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
