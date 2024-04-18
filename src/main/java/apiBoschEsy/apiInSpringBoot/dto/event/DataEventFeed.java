package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record DataEventFeed(
        Long event_id,
        String nameOfEvent,
        String initialDate,
        LocalTime initialTime,
        LocalTime finishTime,
        String description,
        Place local,
        Area responsible_area,
        List<String> imgUrl

) {
    public DataEventFeed(Event event, String initialDate){
        this(
                event.getEvent_id(),
                event.getNameOfEvent(),
                initialDate,
                event.getInitial_time(),
                event.getFinish_time(),
                event.getDescription(),
                event.getLocalEvent(),
                event.getResponsible_area(),
                event.getImgUrl()
        );
    }
}
