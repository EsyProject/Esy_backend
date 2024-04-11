package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataTimeTicket(
        Long id,
        LocalTime initialTime,
        LocalTime finishTime
) {
    public DataTimeTicket(Ticket ticket){
        this(
                ticket.getTicket_id(),
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket()
        );
    }
}
