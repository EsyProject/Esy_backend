package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.constants.Area;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Table(name = "events")
@Entity(name = "Event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "event_id")
public class Event {
    // Attributes of Events

    // Description
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;
    @Column (unique = true)
    private String nameOfEvent;
    @Embedded
    private Area responsible_area;
    @Embedded
    private Area access_event;
    private String description;
    private List<String> imageUrl;

    // Realization
    @Embedded
    private Place localEvent;
    private LocalTime initial_time;
    private LocalTime finish_time;
    private LocalDate initial_date;
    private LocalDate finish_date;

    // Tickets
    private LocalDate initial_date_ticket;
    private LocalDate finish_date_ticket;
    private LocalTime initial_time_ticket;
    private LocalTime finish_time_ticket;

    // Date and hour request
    private LocalDate date_created;
    private LocalTime time_created;

    public Event(DataRegisterEvent data){
        this.nameOfEvent = data.nameOfEvent();
        this.responsible_area = data.responsible_area();
        this.access_event = data.access_event();
        this.description = data.description();
        this.localEvent = data.localEvent();
        this.initial_time = data.initialTime();
        this.finish_time = data.finishTime();
        this.initial_date = data.initialDate();
        this.finish_date = data.finishDate();
        this.initial_date_ticket = data.initialDateTicket();
        this.finish_date_ticket = data.finishDateTicket();
        this.initial_time_ticket = data.initialTimeTicket();
        this.finish_time_ticket = data.finishTimeTicket();
        this.date_created = LocalDate.now();
        this.time_created = LocalTime.now();
    }
}
