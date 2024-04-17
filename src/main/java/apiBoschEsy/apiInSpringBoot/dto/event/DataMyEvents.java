package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record DataMyEvents(
        Long event_id,
        String nameOfEvent,
        LocalDate initialDate,
        LocalTime initialTime,
        Place local,
        Area responsible_are
) {
    public DataMyEvents(Event event){
        this(
                event.getEvent_id(),
                event.getNameOfEvent(),
                event.getInitial_date(),
                event.getInitial_time(),
                event.getLocalEvent(),
                event.getResponsible_area()
        );
    }
}
