package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.assessment.DataDetailAssessment;
import apiBoschEsy.apiInSpringBoot.dto.assessment.DataEvaluationAverage;
import apiBoschEsy.apiInSpringBoot.dto.assessment.DataRegisterAssessment;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.infra.exception.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.service.assessment.AssessmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    // Service Assessment
    @Autowired
    private AssessmentService assessmentService;


    // POST Assessment
    @PostMapping("/{event_id}")
    public ResponseEntity<DataDetailAssessment> registerAssesment(@PathVariable Long event_id, @RequestBody @Valid DataRegisterAssessment dataRegisterAssessment, UriComponentsBuilder uriBuild) throws EventNotFoundException {
        var assessment = assessmentService.createAssessment(dataRegisterAssessment, event_id);
        var uri = uriBuild.path("/assessment/{id}").build(assessment.assessment_id());
        return ResponseEntity.created(uri).body(assessment);
    }

    // GET events with assessment
    @GetMapping("/comments/{event_id}")
    public ResponseEntity<Stream> returnEventWithComment(@PathVariable Long event_id){
        var list = assessmentService.eventComment(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/assessments/{event_id}")
    public ResponseEntity<Stream> returnEventWithAssessment(@PathVariable Long event_id){
        var list = assessmentService.eventAssessment(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/average/{event_id}")
    public ResponseEntity<DataEvaluationAverage> returnAverage(@PathVariable Long event_id) throws EventNotFoundException {
        var average = assessmentService.average(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(average);
    }


}
