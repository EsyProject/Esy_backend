package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.entity.Event;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

public record DataCardEvent(
        Long event_id,
        String initialDate,
        String nameOfEvent,
        String description
) {
    public DataCardEvent(Event event, String initialDate){
        this(
                event.getEvent_id(),
                initialDate,
                event.getNameOfEvent(),
                event.getDescription()
        );
    }
}
