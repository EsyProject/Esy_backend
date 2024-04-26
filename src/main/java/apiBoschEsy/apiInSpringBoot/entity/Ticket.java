package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Ticket")
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ticket_id")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;
    private LocalDate initialDateTicket;

    private LocalDate finishDateTicket;

    private LocalTime initialTimeTicket;

    private LocalTime finishTimeTicket;

    private Boolean isPresence;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_created")
    private LocalDate date_created;
    private LocalTime time_create;

    // Creating a relationShip with others tables
        // Ticket and User (Many tickets has one user)
//        @ManyToOne
//        // For don't create the second database
//        private User user;
        @OneToOne
        private Event event;

    // Creating a constructor for DataRegisterTicker
    public Ticket(DataRegisterTicket data){
        this.initialDateTicket = data.initialDateTicket();
        this.finishDateTicket = data.finishDateTicket();
        this.initialTimeTicket = data.initialTimeTicket();
        this.finishTimeTicket = data.finishTimeTicket();
        this.isPresence = false;
    }
}