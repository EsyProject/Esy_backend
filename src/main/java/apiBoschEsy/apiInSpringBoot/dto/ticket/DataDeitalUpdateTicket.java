package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataDeitalUpdateTicket(
        Long ticket_id,
        List<String> imageUrl,
        String author,
        LocalDate date_created,
        LocalTime time_created
) {
    public DataDeitalUpdateTicket(Ticket ticket){
        this(
                ticket.getTicket_id(),
                ticket.getImageUrl(),
                ticket.getAuthor(),
                ticket.getDate_created(),
                ticket.getTime_create()
        );
    }
}
