package apiBoschEsy.apiInSpringBoot.dto.assessment;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

public record DataListAssessment(
        Long assessment_id,
        String suggestion,
        String description_comment,
        Integer assessment,
        String hour,
        String date_created) {
    public DataListAssessment(Assessment assessment, String date_created){
        this(assessment.getAssessment_id(), assessment.getSuggestion(), assessment.getDescription_comment(), assessment.getAssessment(), assessment.getHour(), date_created);
    }
}
