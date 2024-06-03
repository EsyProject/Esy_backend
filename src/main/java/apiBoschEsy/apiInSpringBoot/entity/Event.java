package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataToUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "event")
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
    private Area responsible_area;
    @Column(columnDefinition = "TEXT")
    private String description;
    private List<String> imgUrl;

    // Realization
    private Place localEvent;
    private LocalTime initial_time;
    private LocalTime finish_time;
    private LocalDate initial_date;
    private LocalDate finish_date;

    // Date and hour request
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_created")
    private LocalDate dateCreated;
    private LocalTime time_created;

    // Deleted ?
    private Boolean delete;
    private String author;
    private Boolean allowMultipleTicketsPerUser = false;

    // Creating a Relationship with others tables
        // Event with Ticket
        @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
//        @JoinColumn(name = "ticket_id", nullable  = true) // Posso criar um evento, sem criar um ticket ?
        private List<Ticket> tickets = new ArrayList<>();
        // Event with Assessment
        @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
        private List<Assessment> assessments = new ArrayList<>();

    public Event(DataRegisterEvent data){
        this.nameOfEvent = data.nameOfEvent();
        this.responsible_area = data.responsible_area();
        this.description = data.description();
        this.localEvent = data.localEvent();
        this.initial_time = data.initialTime();
        this.finish_time = data.finishTime();
        this.initial_date = data.initialDate();
        this.finish_date = data.finishDate();
        this.delete = false;
    }

    // PUT the event
    @Deprecated
    public void updateTheInformationsOfEvent(DataToUpdate data){
        if(data.responsible_area() != null){
            setResponsible_area(data.responsible_area());
        }
        if(data.description() != null){
            setDescription(data.description());
        }
        if(data.localEvent() != null){
            setLocalEvent(data.localEvent());
        }
        if(data.initialDate() != null){
            setInitial_date(data.initialDate());
        }
        if(data.finishDate() != null){
            setFinish_date(data.finishDate());
        }
        if(data.initialTime() != null){
            setInitial_time(data.initialTime());
        }
        if(data.finishTime() != null){
            setFinish_time(data.finishTime());
        }
    }

    public boolean isAllowMultipleTicketsPerUser() {
        return allowMultipleTicketsPerUser;
    }
}
