package apiBoschEsy.apiInSpringBoot.service.email;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.email.DataDetailEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.entity.Email;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEmail;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.crypto.Data;
import java.time.LocalDate;

@Service
public class EmailService {

    @Autowired
    private IRepositoryEmail repositoryEmail;
    @Autowired
    private FormatService formatService;
    @Autowired
    private JavaMailSender emailSender;

//    public DataDetailEmail sendEmail(DataRegisterEmail dataRegisterEmail){
//        try{
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(dataRegisterEmail.emailFrom());
//            message.setTo(dataRegisterEmail.emailTo());
//            message.setSubject(dataRegisterEmail.emailTo());
//            message.setText(dataRegisterEmail.body());
//
//            dataRegisterEmail.(StatusEmail.SENT);
//        }catch (MailException error){
//            email.setStatusEmail(StatusEmail.ERROR);
//        }
//        return new DataDetailEmail(
//                email.getEmail_id(),
//                email.getOwnerRef(),
//                email.getEmailFrom(),
//                email.getEmailTo(),
//                email.getTitle_email(),
//                email.getBody(),
//                formatService.formattedDate(email.getSendDateEmail())
//        );
//    }
}
