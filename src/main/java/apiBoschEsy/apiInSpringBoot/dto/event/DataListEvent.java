package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record DataListEvent(
        Long event_id,
        String nameOfEvent,
        Area responsible_area,
        Area access_event,
        String description,
        Place localEvent,
        String initialDate,
        String finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        List<String> imgUrl
) {
    public DataListEvent(Event event, String initialDate, String finishDate){
        this(
                event.getEvent_id(),
                event.getNameOfEvent(),
                event.getResponsible_area(),
                event.getAccess_event(),
                event.getDescription(),
                event.getLocalEvent(),
                initialDate,
                finishDate,
                event.getInitial_time(),
                event.getFinish_time(),
                event.getImgUrl()
        );
    }

}
