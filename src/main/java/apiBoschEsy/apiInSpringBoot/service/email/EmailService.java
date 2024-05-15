package apiBoschEsy.apiInSpringBoot.service.email;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.email.DataDetailEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import apiBoschEsy.apiInSpringBoot.entity.Email;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEmail;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EmailService {

    @Autowired
    private IRepositoryEmail repositoryEmail;

   @Autowired
   private JavaMailSender javaMailSender;
   @Autowired
   private FormatService formatService;
   @Autowired
   private IRepositoryTicket repositoryTicket;

   @Value("${spring.mail.username}")
   private String username;


    // POST
    @Transactional
    public DataDetailEmail sendingEmail(@AuthenticationPrincipal Jwt jwt, DataRegisterEmail dataRegisterEmail){

        // Creating a user
        DataAuth user = new DataAuth(jwt);

        Email email = new Email(dataRegisterEmail);
        email.setSendDateEmail(LocalDate.now());
        email.setTimeEmail(LocalTime.now());
        email.setEmailTo(email.getEmailTo());
        email.setEmailFrom(email.getEmailFrom());
        email.setBody(email.emailDefaultSendTicket(user.userName()));

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(email.getEmailFrom());
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setText(email.emailDefaultSendTicket(user.userName()));
            mimeMessageHelper.setSubject(email.getTitle_email());
            javaMailSender.send(mimeMessage);
            email.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {
            repositoryEmail.save(email);
            return new DataDetailEmail(email, formatService.formattedDate(email.getSendDateEmail()));
        }
    }

}
