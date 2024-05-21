package apiBoschEsy.apiInSpringBoot.service.email;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.email.DataDetailEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import apiBoschEsy.apiInSpringBoot.entity.Email;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEmail;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private IRepositoryEmail repositoryEmail;
    @Autowired
    private IRepositoryEvent repositoryEvent;

   @Autowired
   private JavaMailSender javaMailSender;
   @Autowired
   private FormatService formatService;
   @Autowired
   private IRepositoryTicket repositoryTicket;
   @Autowired
   private EmailDefault emailDefault;

   @Value("${spring.mail.username}")
   private String username;


    // POST
    @Transactional
    public DataDetailEmail sendingEmail(@AuthenticationPrincipal Jwt jwt, @PathVariable Long event_id, @RequestBody @Valid DataRegisterEmail dataRegisterEmail) throws EventNotFoundException {

        // Creating a user
        DataAuth user = new DataAuth(jwt);

        // Get Event
        var event = repositoryEvent.findById(event_id);
        var nameOfEvent = event.get().getNameOfEvent();
        if(event.isEmpty()) {
            throw new EventNotFoundException("Event not Found with ID: " + event_id);
        }

        List<Ticket> tickets = event.get().getTickets();
        String imageTicket = null;

        for (Ticket ticket: tickets) {
            if(ticket.getAuthor() != null && ticket.getAuthor().equals(user.userName())){
                imageTicket = ticket.getImageUrl().toString();
                break;
            }
        }

        Email email = new Email(dataRegisterEmail);
        email.setSendDateEmail(LocalDate.now());
        email.setTimeEmail(LocalTime.now());
        email.setEmailTo(email.getEmailTo());
        email.setEmailFrom(email.getEmailFrom());
        email.setOwnerRef(user.userName());
        email.setBody(emailDefault.emailDefault(nameOfEvent, user));
        email.setTitle_email(emailDefault.titleEmail(nameOfEvent));

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(email.getEmailFrom());
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setText(emailDefault.emailDefault(nameOfEvent,user));
            mimeMessageHelper.setSubject(email.getTitle_email());

            if(imageTicket != null){
                FileSystemResource file = new FileSystemResource(new File(imageTicket));
                mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
            javaMailSender.send(mimeMessage);
            email.setStatusEmail(StatusEmail.SENT);

        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println(e.getMessage());
        }finally {
            repositoryEmail.save(email);
            return new DataDetailEmail(email, formatService.formattedDate(email.getSendDateEmail()));
        }
    }

}
