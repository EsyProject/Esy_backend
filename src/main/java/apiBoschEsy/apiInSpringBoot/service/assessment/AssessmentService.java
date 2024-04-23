package apiBoschEsy.apiInSpringBoot.service.assessment;

import apiBoschEsy.apiInSpringBoot.dto.assessment.*;
import apiBoschEsy.apiInSpringBoot.dto.comment.DataComment;
import apiBoschEsy.apiInSpringBoot.dto.comment.DataEventWithComments_feed;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.infra.exception.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.service.utils.EvaluationAverage;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DataDetailAssessment createAssessment(DataRegisterAssessment dataRegisterAssessment, Long event_id) throws EventNotFoundException {
        // Get event
        Event event = iRepositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));
        // Connect Event with assessment
        var assessment = new Assessment(dataRegisterAssessment);
        assessment.setEvent(event);
        iRepositoryAssessment.save(assessment);

        return new DataDetailAssessment(assessment, formatService.formattedDate(assessment.getDate_created()));
    }

    // Get Event with all comments
    // Method Stream -> A interface Stream é uma sequência de elementos que suporta várias operações, como filtragem, mapeamento, redução, etc.
    public Stream eventComment(@PathVariable Long event_id) {
        var events = iRepositoryEvent.findById(event_id).stream();
        return events.map(event -> new DataEventWithComments_feed(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date())));
    }

    // Get Event with all Assessments
    public Stream eventAssessment(@PathVariable Long event_id) {
        var events = iRepositoryEvent.findById(event_id).stream();
        return events.map(event -> new DataAssessmentEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date())));
    }

    // Get comments
    public Stream commentEvent(@PathVariable Long event_id) {
        var assessment = iRepositoryAssessment.findById(event_id).stream();
        return assessment.map(assessment1 -> new DataComment(assessment1, formatService.formattedDate(assessment1.getDate_created())));
    }

    // Nota of Event (All asessment)
    public DataEvaluationAverage average(@PathVariable Long event_id) throws EventNotFoundException {
        var average = evaluationAverage.avarageOfAssessmentEvent(event_id);
        return new DataEvaluationAverage(average);
    }

}
