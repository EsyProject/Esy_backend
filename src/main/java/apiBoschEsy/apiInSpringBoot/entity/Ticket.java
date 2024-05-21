package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private String author;
    private String qrCodeNumber;
    private Boolean isPresence;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_created")
    private LocalDate date_created;
    private LocalTime time_create;

    private List<String> imageUrl;

    // Creating a relationShip with others tables
        @ManyToOne
        @JoinColumn(name = "event_id", nullable = true)
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