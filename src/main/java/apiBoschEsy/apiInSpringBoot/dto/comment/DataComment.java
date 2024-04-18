package apiBoschEsy.apiInSpringBoot.dto.comment;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;

public record DataComment(
        Long id,
        String name,
        String description_comment,
        String date_created,
        String assessment
) {
    public DataComment(Assessment assessment, String date_created){
        this(assessment.getAssessment_id(),assessment.getName(), assessment.getDescription_comment(), date_created, assessment.getAssessment());
    }
}
