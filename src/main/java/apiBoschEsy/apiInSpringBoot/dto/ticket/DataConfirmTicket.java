package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalTime;

public record DataConfirmTicket(
        Long ticket_id,
        String author,
        Boolean isPresence,
        String date_created,
        LocalTime time_created
) {
    public DataConfirmTicket(Ticket ticket, String date_created){
        this(
                ticket.getTicket_id(),
                ticket.getAuthor(),
                ticket.getIsPresence(),
                date_created,
                ticket.getTime_create()
        );
    }
}
