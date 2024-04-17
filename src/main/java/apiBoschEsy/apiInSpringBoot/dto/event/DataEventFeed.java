package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record DataEventFeed(
        Long event_id,
        String nameOfEvent,
        LocalDate initialDate,
        LocalTime initialTime,
        LocalTime finishTime,
        String description,
        Place local,
        Area responsible_area

) {
    public DataEventFeed(Event event){
        this(
                event.getEvent_id(),
                event.getNameOfEvent(),
                event.getInitial_date(),
                event.getInitial_time(),
                event.getFinish_time(),
                event.getDescription(),
                event.getLocalEvent(),
                event.getResponsible_area()

        );
    }
}
