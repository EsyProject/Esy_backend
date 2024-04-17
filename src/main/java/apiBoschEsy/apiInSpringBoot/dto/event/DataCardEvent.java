package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.entity.Event;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

public record DataCardEvent(
        Long event_id,
        LocalDate initialDate,
        String nameOfEvent,
        String description
) {
    public DataCardEvent(Event event){
        this(
                event.getEvent_id(),
                event.getInitial_date(),
                event.getNameOfEvent(),
                event.getDescription()
        );
    }
}
