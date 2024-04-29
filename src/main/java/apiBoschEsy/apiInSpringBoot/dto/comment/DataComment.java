package apiBoschEsy.apiInSpringBoot.dto.comment;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;

public record DataComment(
        Long comment_id,
        String author,
        String description_comment,
        String date_created,
        Integer assessment
) {
    public DataComment(Assessment assessment, String date_created){
        this(
                assessment.getAssessment_id(),
                assessment.getAuthor(),
                assessment.getDescription_comment(),
                date_created,
                assessment.getAssessment()
        );
    }
}
