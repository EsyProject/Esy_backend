package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.place.Place;
import jakarta.persistence.*;
import lombok.*;

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
    private Place place;
    private String data;
    private String hourEvent;
    private String responsible;
    private Place constPlace;
    private String description;
    private String duration;
    private String imageUrl;
    private Place participatingAreas;
    private String numberOfParticipants;

    public Event(DataRegisterEvent data) {
        this.nameOfEvent = data.nameOfEvent();
        this.category = data.category();
        this.place = data.place();
        this.data = data.data();
        this.hourEvent = data.hourEvent();
        this.responsible = data.responsible();
        this.constPlace = data.costPlace();
        this.description = data.description();
        this.duration = data.duration();
        this.imageUrl = data.imageURL();
        this.participatingAreas = data.participatingAreas();
        this.numberOfParticipants = data.numberOfParticipants();
    }
}
