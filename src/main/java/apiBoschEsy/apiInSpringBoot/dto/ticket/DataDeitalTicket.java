package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataDeitalTicket(
        Long ticket_id,
        LocalDate initialDate,
        LocalDate finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        String date_created,
        String timeCreated
) {
    public DataDeitalTicket(Ticket ticket, String initialDate, String finishDate){
        this(
                ticket.getTicket_id(),
                ticket.getInitialDateTicket(),
                ticket.getFinishDateTicket(),
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket(),
                initialDate,
                finishDate
        );
    }
}
