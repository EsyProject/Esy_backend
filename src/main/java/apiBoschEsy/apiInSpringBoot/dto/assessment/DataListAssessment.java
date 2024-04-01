package apiBoschEsy.apiInSpringBoot.dto.assessment;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

public record DataListAssessment(
        Long id,
        String name,
        String suggestion,
        String description_comment,
        String assessment,
        String hour,
        String date_created) {
    public DataListAssessment(Assessment assessment){
        this(assessment.getId(),assessment.getName(), assessment.getSuggestion(), assessment.getDescription_comment(), assessment.getAssessment(), assessment.getHour(), assessment.getDate_created());
    }
}
