package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.assessment.DataListAssessment;
import apiBoschEsy.apiInSpringBoot.dto.assessment.DataRegisterAssessment;
import apiBoschEsy.apiInSpringBoot.dto.comment.DataComment;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Optional;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    // Creating access with database

    // Access the Repository Assessment
    @Autowired
    private IRepositoryAssessment repositoryAssessment;

    // Access the Repository Comment


    // POST Assessment
    @PostMapping
    @Transactional
    public void registerAssessment(@RequestBody @Valid DataRegisterAssessment dataRegisterAssessment){
        repositoryAssessment.save(new Assessment(dataRegisterAssessment));
    }

    // GET ALL Assessment
    @GetMapping("/assessments")
    public Page<DataListAssessment> listAssessment(@PageableDefault(size = 4, sort = {"id", "name"}) Pageable pageable){
        var list = repositoryAssessment.findAll(pageable).map(DataListAssessment::new);
        return list;
    }

    // GET byId Assessment
    @GetMapping("/{id}")
    public ResponseEntity<Object> listAssessmentById(@PathVariable Long id){
        Optional<Assessment> assessment = repositoryAssessment.findById(id);
        if(assessment.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assessment not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(assessment.get());
    }

    // GET all Comments
    @GetMapping("/comments")
    public Page<DataComment> listComment(@PageableDefault(size = 4, sort = {"id", "name"}) Pageable pageable){
        var listComment = repositoryAssessment.findAll(pageable).map(DataComment::new);
        return  listComment;
    }

     // GET Comment by id
    @GetMapping("/comment/{id}")
    public ResponseEntity<DataComment> listByIdComment(@PathVariable Long id){
        Optional<Assessment> commentOptional = repositoryAssessment.findById(id);
        return commentOptional.map(comment -> ResponseEntity.ok(new DataComment(comment)))
                .orElse(ResponseEntity.notFound().build());
    }
}
