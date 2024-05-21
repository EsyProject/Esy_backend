package apiBoschEsy.apiInSpringBoot.dto.email;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import apiBoschEsy.apiInSpringBoot.entity.Email;

public record DataDetailEmail(
        Long email_id,
        String emailFrom,
        String emailTo,
        String title_email,
        String body,
        StatusEmail statusEmail,
        String dateSend
) {
    public DataDetailEmail(Email email, String dateSend){
        this(
          email.getEmail_id(),
          email.getEmailFrom(),
          email.getEmailTo(),
          email.getTitle_email(),
          email.getBody(),
          email.getStatusEmail(),
          dateSend
        );
    }
}
