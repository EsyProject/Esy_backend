package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.place.Area;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Table(name = "events")
@Entity(name = "Event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {
    // Attributes of Events
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfEvent;
    private String category;
    private Area place;
    private String date;
    private String hourEvent;
    private String responsible;
    private Area constPlace;
    private String description;
    private String duration;
    private String imageUrl;
    private Area participatingAreas;
    private Integer numberQRCode;
    private String numberOfParticipants;
    private String date_created;
    private String hour;

    public Event(DataRegisterEvent data) {
        this.nameOfEvent = data.nameOfEvent();
        this.category = data.category();
        this.place = data.place();
        this.date = data.data();
        this.hourEvent = data.hourEvent();
        this.responsible = data.responsible();
        this.constPlace = data.costPlace();
        this.description = data.description();
        this.duration = data.duration();
        this.participatingAreas = data.participatingAreas();
        this.numberOfParticipants = data.numberOfParticipants();
        this.date_created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.hour = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
