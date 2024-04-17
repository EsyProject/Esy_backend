package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataToUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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
    private Area access_event;
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

    // Creating a Relationship with others tables
        // Event with Ticket
        @OneToMany
        @JoinColumn(name = "ticket_id", nullable  = true) // Posso criar um evento, sem criar um ticket ?
        private List<Ticket> tickets;
        // Event with Assessment
        @OneToMany
        @JoinColumn(name = "assessment_id", nullable = true)
        private List<Assessment> assessments;

    // Creating a method for put event
    public void toUpdateInfoEvent(DataToUpdate dataToUpdate){
        if(dataToUpdate.description() != null){
            this.description = dataToUpdate.description();
        }
        if(dataToUpdate.imgUrl() != null){
            this.imgUrl = dataToUpdate.imgUrl();
        }
        if(dataToUpdate.initialDate() != null){
            this.finish_date = dataToUpdate.initialDate();
        }
        if(dataToUpdate.finishDate() != null){
            this.finish_date = dataToUpdate.finishDate();
        }
        if(dataToUpdate.initialTime() != null){
            this.initial_time = dataToUpdate.initialTime();
        }
        if(dataToUpdate.finishTime() !=null){
            this.finish_time = dataToUpdate.finishTime();
        }
    }

    // Method for delete Event
    public Boolean delete(){
        this.delete = true;
        return true;
    }

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
        this.delete = false;
    }
}
