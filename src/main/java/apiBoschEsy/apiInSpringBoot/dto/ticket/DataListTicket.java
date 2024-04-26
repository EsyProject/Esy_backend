package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;

public record DataListTicket(
        Long id,
        LocalDate initialDate,
        LocalDate finishDate,
        LocalTime initialTime,
        LocalTime finishTime
) {
    public DataListTicket(Ticket ticket){
        this(
                ticket.getTicket_id(),
                ticket.getInitialDateTicket(),
                ticket.getFinishDateTicket(),
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket()
        );
    }
}
