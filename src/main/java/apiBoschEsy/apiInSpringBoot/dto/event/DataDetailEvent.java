package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.place.Area;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

public record DataDetailEvent(
        String nameOfEvent,
        String category,
        Area place,
        String date,
        LocalTime hourEvent,
        String responsible,
        Area costPlace,
        String description,
        String duration,
        MultipartFile imageEvent,
        Area participatingAreas,
        String numberOfParticipants
) {
    public DataDetailEvent(Event event){
        this(event.getNameOfEvent(), event.getCategory(), event.getPlace(), event.getDate(), );
    }
}
