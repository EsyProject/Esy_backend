package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.entity.Event;

public record DataNameEvent(
        Long event_id,
        String nameOfEvent
) {
    public DataNameEvent(Event event) {
        this(
                event.getEvent_id(),
                event.getNameOfEvent()
        );
    }
}
