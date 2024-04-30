package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

//    @PostMapping
//    public ResponseEntity<DataDetailEmail> sendingEmail(
//            @RequestBody @Valid DataRegisterEmail dataRegisterEmail,
//            UriComponentsBuilder uriBuilder
//            ){
//        var email = emailService.sendEmail(dataRegisterEmail);
//        var uri = uriBuilder.path("/email/{email_id}").build(emailModel.getEmail_id());
//
//        return ResponseEntity.created(uri).body(email);
//    }
}
