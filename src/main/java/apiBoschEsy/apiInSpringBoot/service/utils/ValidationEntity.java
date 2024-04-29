package apiBoschEsy.apiInSpringBoot.service.utils;

import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Deprecated
public class ValidationEntity {

    // Access the database
    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private IRepositoryTicket repositoryTicket;
    @Autowired
    private IRepositoryAssessment repositoryAssessment;

    public <T> boolean validationIsPresent(Optional<T> optionalT){
        return optionalT.isPresent();
    }
    public boolean validateObjects() {
        boolean eventExists = validationIsPresent(Optional.ofNullable(repositoryEvent));
        boolean ticketExists = validationIsPresent(Optional.ofNullable(repositoryTicket));
        boolean assessmentExists = validationIsPresent(Optional.ofNullable(repositoryAssessment));

        return eventExists && ticketExists && assessmentExists;
    }
}
