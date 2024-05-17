package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.dashboard.DataEvaluationAverage;
import apiBoschEsy.apiInSpringBoot.dto.dashboard.DataHighPoints;
import apiBoschEsy.apiInSpringBoot.dto.dashboard.DataSuggestion;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.service.dashboard.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
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
}
