package apiBoschEsy.apiInSpringBoot.dto.dashboard;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;

public record DataSuggestion(
        Long id,
        String date_suggestion,
        String message_suggestion
) {
    public DataSuggestion(Assessment assessment, String date){
        this(
                assessment.getAssessment_id(),
                date,
                assessment.getSuggestion()
        );
    }
}
