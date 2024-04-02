package apiBoschEsy.apiInSpringBoot.dto.comment;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;

public record DataComment(
        Long id,
        String name,
        String description_comment,
        String date_created,
        String assessment
) {
    public DataComment(Assessment assessment){
        this(assessment.getId(),assessment.getName(), assessment.getDescription_comment(), assessment.getDate_created(), assessment.getAssessment());
    }
}
