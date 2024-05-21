package apiBoschEsy.apiInSpringBoot.dto.assessment;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataAssessmentEvent(
        Long event_id,
        String nameOfEvent,
        Area responsible_area,
        String description,
        List<String> imgUrl,
        Place localEvent,
        String initialDate,
        String finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        @JsonIgnoreProperties("event")
        List<Assessment> assessments
) {
    public DataAssessmentEvent(Event event, String initialDate, String finishDate){
        this(
                event.getEvent_id(),
                event.getNameOfEvent(),
                event.getResponsible_area(),
                event.getDescription(),
                event.getImgUrl(),
                event.getLocalEvent(),
                initialDate,
                finishDate,
                event.getInitial_time(),
                event.getFinish_time(),
                event.getAssessments()
        );
    }
}
