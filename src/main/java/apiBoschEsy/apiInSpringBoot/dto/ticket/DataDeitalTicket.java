package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataDeitalTicket(
        Long ticket_id,
        LocalDate initialDate,
        LocalDate finishDate,
        LocalTime initialTime,
        LocalTime finishTime
) {
    public DataDeitalTicket(Ticket ticket){
        this(
                ticket.getTicket_id(),
                ticket.getInitialDate(),
                ticket.getFinishDate(),
                ticket.getInitialTime(),
                ticket.getFinishTime()
        );
    }
}
