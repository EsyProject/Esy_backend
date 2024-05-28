package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.dashboard.*;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.service.dashboard.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashBoardService dashBoardService;
    // Average
    @GetMapping("/average/{event_id}")
    public ResponseEntity<DataEvaluationAverage> getAverageEvent(@PathVariable Long event_id) throws EventNotFoundException {
        var average = dashBoardService.average(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(average);
    }
    // HighPoints
    @GetMapping("/highPoints/{event_id}")
    public ResponseEntity<DataHighPoints> getHighPoints(@PathVariable Long event_id) throws EventNotFoundException {
        var highPoints = dashBoardService.highPoints(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(highPoints);
    }

    // Suggestion
    @GetMapping("/suggestion/{event_id}")
    public ResponseEntity<Stream<DataSuggestion>> getAllSuggestion(@PathVariable Long event_id) throws EventNotFoundException {
        var suggestion = dashBoardService.returnSuggestion(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(suggestion);
    }

    // Inscription
    @GetMapping("/inscription/{event_id}")
    public ResponseEntity<DataInscription> getNumberOfTicketEvents(@PathVariable Long event_id)throws EventNotFoundException{
        var inscription = dashBoardService.numberOfInscription(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(inscription);
    }

    // Participation
    @GetMapping("/participation/{event_id}")
    public ResponseEntity<DataParticipation> getNumberOfParticipation(@PathVariable Long event_id) throws EventNotFoundException{
        var participation = dashBoardService.getNumberParticipation(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(participation);
    }

    // Absence
    @GetMapping("/absence/{event_id}")
    public ResponseEntity<DataAbsence> getNumberAbsence(@PathVariable Long event_id) throws EventNotFoundException {
        var absence = dashBoardService.getNumberAbsense(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(absence);
    }
}
