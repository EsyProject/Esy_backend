package apiBoschEsy.apiInSpringBoot.service.email;

import apiBoschEsy.apiInSpringBoot.entity.Email;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private IRepositoryEmail repositoryEmail;

    public void sendEmail(Email email) {

    }
}
