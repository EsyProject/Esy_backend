package apiBoschEsy.apiInSpringBoot.service.assessment;

import apiBoschEsy.apiInSpringBoot.dto.assessment.*;
import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.comment.DataComment;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.service.utils.EvaluationAverage;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Stream;

@Service
public class AssessmentService {
    // Access the database

    // Access the assessment database
    @Autowired
    private IRepositoryAssessment iRepositoryAssessment;

    // Access the event database
    @Autowired
    private IRepositoryEvent iRepositoryEvent;

    // Service Date
    @Autowired
    private FormatService formatService;

    @Autowired
    private EvaluationAverage evaluationAverage;


    // POST Assessment (Based in a Event)
    @Transactional
    public DataDetailAssessment createAssessment(DataRegisterAssessment dataRegisterAssessment, Long event_id, @AuthenticationPrincipal Jwt jwt) throws EventNotFoundException {
        // Get the Token JWT SSO
        DataAuth user = new DataAuth(jwt);
        // Get event
        Event event = iRepositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));
        // Connect Event with assessment
        var assessment = new Assessment(dataRegisterAssessment);
        assessment.setEvent(event);
        assessment.setAuthor(user.userName());
        iRepositoryAssessment.save(assessment);

        return new DataDetailAssessment(assessment, formatService.formattedDate(assessment.getDate_created()), user.userName());
    }

    // Gets the Event with all comments
    // Stream Method -> The Stream interface is a sequence of elements that supports various operations such as filtering, mapping, reduction, etc.
    public Stream eventComment(@PathVariable Long event_id) throws EventNotFoundException {
        var event = iRepositoryEvent.findById(event_id);
        if(!event.isPresent()){
            throw new EventNotFoundException("Event Not Found!");
        }
        var eventWithAssessment = event.get();
        var assessment = eventWithAssessment.getAssessments();
        return assessment.stream().map(assessment1 -> new DataComment(assessment1, formatService.formattedDate(assessment1.getDate_created())));
    }

    // Get Event with all Assessments
    public Stream eventAssessment(@PathVariable Long event_id) {
        var events = iRepositoryEvent.findById(event_id).stream();
        return events.map(event -> new DataAssessmentEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date())));
    }
}
