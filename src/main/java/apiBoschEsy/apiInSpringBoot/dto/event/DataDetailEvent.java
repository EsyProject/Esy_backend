package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import javax.xml.crypto.Data;
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
        LocalDate initialDate,
        LocalDate finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        LocalDate date_created,
        LocalTime hour_created
) {
    public DataDetailEvent(Event event){
       this(
               event.getEvent_id(),
               event.getNameOfEvent(),
               event.getResponsible_area(),
               event.getAccess_event(),
               event.getDescription(),
               event.getImgUrl(),
               event.getLocalEvent(),
               event.getInitial_date(),
               event.getFinish_date(),
               event.getInitial_time(),
               event.getFinish_time(),
               event.getDate_created(),
               event.getTime_created()
       );
    }

    }
