package apiBoschEsy.apiInSpringBoot.dto.assessment;

import apiBoschEsy.apiInSpringBoot.constants.HighlightPoint;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;

import java.util.List;

public record DataDetailAssessment(
        Long assessment_id,
        String author,
        String suggestion,
        String description_comment,
        Integer assessment,
        String hour,
        String date_created,
        HighlightPoint highlightPoint

) {
    public DataDetailAssessment(Assessment assessment, String date_created, String author){
        this(
                assessment.getAssessment_id(),
                author,
                assessment.getSuggestion(),
                assessment.getDescription_comment(),
                assessment.getAssessment(),
                assessment.getHour(),
                date_created,
                assessment.getHighlightPoint()
        );
    }
}
