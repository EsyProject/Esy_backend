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
    private LocalDate initialDate;

    private LocalDate finishDate;

    private LocalTime initialTime;

    private LocalTime finishTime;

    private Boolean presence;

    // Creating a constructor for DataRegisterTicker
    public Ticket(DataRegisterTicket data){
        this.initialDate = data.initialDateTicket();
        this.finishDate = data.finishDateTicket();
        this.initialTime = data.initialTimeTicket();
        this.finishTime = data.finishTimeTicket();
        this.presence = false;
    }
}