package apiBoschEsy.apiInSpringBoot.service.assessment;

import apiBoschEsy.apiInSpringBoot.dto.assessment.DataDetailAssessment;
import apiBoschEsy.apiInSpringBoot.dto.assessment.DataRegisterAssessment;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryAssessment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {
    @Autowired
    private IRepositoryAssessment repositoryAssessment;
}
