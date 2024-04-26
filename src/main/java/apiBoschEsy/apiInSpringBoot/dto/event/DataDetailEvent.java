package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.constants.Area;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataDetailEvent(
        Long event_id,
        String nameOfEvent,
        Area responsible_area,
        Area access_event,
        String description,
        List<String> imgUrl,
        Place localEvent,
        String initialDate,
        String finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        LocalDate date_created,
        LocalTime time_created,
        String author
) {
    public DataDetailEvent(Event event, String initialDate, String finishDate, String author){
       this(
               event.getEvent_id(),
               event.getNameOfEvent(),
               event.getResponsible_area(),
               event.getAccess_event(),
               event.getDescription(),
               event.getImgUrl(),
               event.getLocalEvent(),
               initialDate,
               finishDate,
               event.getInitial_time(),
               event.getFinish_time(),
               event.getDateCreated(),
               event.getTime_created(),
               author
               );
    }

    }
