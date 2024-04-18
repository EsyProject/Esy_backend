package apiBoschEsy.apiInSpringBoot.dto.assessment;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;

public record DataDetailAssessment(
        Long id,
        String name,
        String suggestion,
        String description_comment,
        String assessment,
        String hour,
        String date_created

) {
    public DataDetailAssessment(Assessment assessment, String date_created){
        this(
                assessment.getId(),
                assessment.getName(),
                assessment.getSuggestion(),
                assessment.getDescription_comment(),
                assessment.getAssessment(),
                assessment.getHour(),
                date_created);
    }
}
