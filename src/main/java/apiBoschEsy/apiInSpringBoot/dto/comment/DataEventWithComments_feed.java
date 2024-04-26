package apiBoschEsy.apiInSpringBoot.dto.comment;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record DataEventWithComments_feed(
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
        @JsonIgnoreProperties("event")
        List<DataComment> comments
) {
    public DataEventWithComments_feed(Event event, String initialDate, String finishDate){
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
                event.getAssessments().stream().map(
                        comments -> new DataComment(comments, initialDate)).collect(Collectors.toList())
        );
    }
}
