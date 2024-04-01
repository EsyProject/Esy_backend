package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.assessment.DataListAssessment;
import apiBoschEsy.apiInSpringBoot.dto.assessment.DataRegisterAssessment;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    // All repositories for access the database
    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private IRepositoryAssessment repositoryAssessment;

    // POST Event
    // GET ALL Event
    // GET byId Event
    // PUT Event
    // DELETE Event

    // POST Assessment
    @PostMapping("/assessment")
    @Transactional
    public void registerAssessment(@RequestBody @Valid DataRegisterAssessment dataRegisterAssessment){
        repositoryAssessment.save(new Assessment(dataRegisterAssessment));
    }

    // GET ALL Assessment
    @GetMapping("/assessments")
    public Page<DataListAssessment> listAssessment(@PageableDefault(size = 4, sort = {"name"}) Pageable pageable){
        var list = repositoryAssessment.findAll(pageable).map(DataListAssessment::new);
        return list;
    }

    // GET byId Assessment
    @GetMapping("/assessment/{id}")
    public ResponseEntity<Object> listAssessmentById(@PathVariable Long id){
        Optional<Assessment> assessment = repositoryAssessment.findById(id);
        if(assessment.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assessment not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(assessment.get());
    }

    // GET Comment

}
