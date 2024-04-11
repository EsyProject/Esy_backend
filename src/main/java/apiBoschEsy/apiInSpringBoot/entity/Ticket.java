package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
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

    private Boolean presence;

    // Creating a constructor for DataRegisterTicker
    public Ticket(DataRegisterTicket data){
        this.initialDateTicket = data.initialDateTicket();
        this.finishDateTicket = data.finishDateTicket();
        this.initialTimeTicket = data.initialTimeTicket();
        this.finishTimeTicket = data.finishTimeTicket();
        this.presence = false;
    }
}