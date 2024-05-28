package apiBoschEsy.apiInSpringBoot.service.dashboard;

import apiBoschEsy.apiInSpringBoot.dto.dashboard.*;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.utils.EvaluationAverage;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import apiBoschEsy.apiInSpringBoot.service.utils.HighPointsIntegerHandler;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Stream;

@Service
public class DashBoardService {
    @Autowired
    private HighPointsIntegerHandler highPointsComponent;
    @Autowired
    private EvaluationAverage evaluationAverage;
    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private IRepositoryTicket repositoryTicket;
    @Autowired
    private IRepositoryAssessment repositoryAssessment;
    @Autowired
    private FormatService formatService;

    // Average
    public DataEvaluationAverage average(@PathVariable Long event_id) throws EventNotFoundException {
        var average = evaluationAverage.avarageOfAssessmentEvent(event_id);
        return new DataEvaluationAverage(average);
    }

    // HighLightPoints

    public DataHighPoints highPoints(@PathVariable Long event_id) throws EventNotFoundException {
        List<String> highlightPoints = Arrays.asList("Alimentação", "Temáticas abordadas", "Pontualidade", "Interações sociais");
        Map<String, Integer> highPointsCounts = new HashMap<>();

        for (String point : highlightPoints) {
            int count = highPointsComponent.returnHigh(event_id, point);
            highPointsCounts.put(point, count);
        }

        return new DataHighPoints(event_id,
                highPointsCounts.getOrDefault("Alimentação", 0),
                highPointsCounts.getOrDefault("Temáticas abordadas", 0),
                highPointsCounts.getOrDefault("Pontualidade", 0),
                highPointsCounts.getOrDefault("Interações sociais", 0));
    }
     // Suggestion
    public Stream<DataSuggestion> returnSuggestion(@PathVariable Long event_id) throws EventNotFoundException {
        var event = repositoryEvent.findById(event_id);
        // Validation
        if(!event.isPresent()){
            throw new EventNotFoundException("Event Not Found");
        }
        var assessment = event.get().getAssessments();

        return assessment.stream().map(assessment1 -> new DataSuggestion(assessment1.getAssessment_id(), formatService.formattedDate(assessment1.getDate_created()), assessment1.getSuggestion()));
    }

    // GET  Inscription
    public DataInscription numberOfInscription(@PathVariable Long event_id) throws EventNotFoundException {

        // Get event
        var event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));

        // Get list of Tickets
        List<Ticket> listOfTickets = event.getTickets();

        Integer numberOfTicketsOnEvent = listOfTickets.size() - 1;

        return new DataInscription(numberOfTicketsOnEvent);
    }

    // GET Participation
    public DataParticipation getNumberParticipation(@PathVariable Long event_id) throws EventNotFoundException {

        // GET event
        var event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not Found with ID: " + event_id));

        // GET listOfTickets
        List<Ticket> listOfTickets = event.getTickets();

        Long presenceCount = listOfTickets.stream()
                .filter(Ticket::getIsPresence)
                .count();

        return new DataParticipation(presenceCount.intValue());
    }

    // GET Absence
    public DataAbsence getNumberAbsense(@PathVariable Long event_id) throws EventNotFoundException {

        // GET Event
        var event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not Found with ID: " + event_id));

        // GET listOfTickets
        List<Ticket> listOfTickets = event.getTickets();

        Long absenceCount = listOfTickets.stream()
                .filter(ticket -> !ticket.getIsPresence())
                .count() - 1;

        return new DataAbsence(Math.toIntExact(absenceCount.intValue()));
    }

    // GET success event
    public 

}
