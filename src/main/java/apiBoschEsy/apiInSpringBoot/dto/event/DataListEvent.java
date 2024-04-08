package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataListEvent(
        Long event_id,
        String nameOfEvent,
        Area responsible_area,
        Area access_event,
        String description,
        Place localEvent,
        LocalDate initialDate,
        LocalDate finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        LocalDate initialDateTicket,
        LocalDate finishDateTicket,
        LocalTime initialTimeTicket,
        LocalTime finishTimeTicket,
        List<String> imageUrl
) {
    public DataListEvent(Event event){
        this(
                event.getEvent_id(),
                event.getNameOfEvent(),
                event.getResponsible_area(),
                event.getAccess_event(),
                event.getDescription(),
                event.getLocalEvent(),
                event.getInitial_date(),
                event.getFinish_date(),
                event.getInitial_time(),
                event.getFinish_time(),
                event.getInitial_date_ticket(),
                event.getFinish_date_ticket(),
                event.getInitial_time_ticket(),
                event.getFinish_time_ticket(),
                event.getImageUrl()
        );
    }

}
