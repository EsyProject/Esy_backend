package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.assessment.DataRegisterAssessment;
import apiBoschEsy.apiInSpringBoot.infra.exception.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.service.assessment.AssessmentService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity registerAssesment(@PathVariable Long event_id, @RequestBody @Valid DataRegisterAssessment dataRegisterAssessment, UriComponentsBuilder uriBuild) throws EventNotFoundException {
        var assessment = assessmentService.createAssessment(dataRegisterAssessment, event_id);
        var uri = uriBuild.path("/assessment/{id}").build(assessment.assessment_id());
        return ResponseEntity.created(uri).body(assessment);
    }

    // GET events with assessment
    @GetMapping("/assessments/{event_id}")
    public ResponseEntity returnEventWithAssessment(@PathVariable Long event_id){
        var list = assessmentService.eventAssessment(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // GET comment based in assessment (Each event)
    @GetMapping("/comments/{assessment_id}")
    public ResponseEntity returnCommentEvent(@PathVariable Long assessment_id){
        var comment = assessmentService.commentEvent(assessment_id);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }
    
//
//    // GET byId Assessment
//    @GetMapping("/{id}")
//    public ResponseEntity listAssessmentById(@PathVariable Long id){
//        Optional<Assessment> assessment = repositoryAssessment.findById(id);
//        if(assessment.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assessment not found");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(assessment.get());
//    }
//
//    // GET all Comments
//    @GetMapping("/comments")
//    public ResponseEntity<Page<DataComment>> listComment(@PageableDefault(size = 4, sort = {"id", "name"}) Pageable pageable){
//        var listComment = repositoryAssessment.findAll(pageable).map(DataComment::new);
//        return  ResponseEntity.ok(listComment);
//    }
//
//     // GET Comment by id
//    @GetMapping("/comment/{id}")
//    public ResponseEntity listByIdComment(@PathVariable Long id){
//        Optional<Assessment> commentOptional = repositoryAssessment.findById(id);
//        return commentOptional.map(comment -> ResponseEntity.ok(new DataComment(comment)))
//                .orElse(ResponseEntity.notFound().build());
//    }
}
