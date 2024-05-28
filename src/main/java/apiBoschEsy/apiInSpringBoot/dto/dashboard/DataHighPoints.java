package apiBoschEsy.apiInSpringBoot.dto.dashboard;

import apiBoschEsy.apiInSpringBoot.entity.Event;

public record DataHighPoints(
        Long event_id,
        Integer food,
        Integer topics_addressed,
        Integer punctuality,
        Integer social_interactions
) {
    public DataHighPoints(Integer food, Integer topics_addressed, Integer punctuality, Integer social_interactions, Long event_id){
        this(
                event_id,
                food,
                topics_addressed,
                punctuality,
                social_interactions
        );
    }
}
