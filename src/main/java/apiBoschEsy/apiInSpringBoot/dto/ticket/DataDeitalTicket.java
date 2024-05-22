package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public record DataDeitalTicket(
        Long ticket_id,
        String event_name,
        String qrCodeNumber,
        String author,
        LocalDate date_created,
        String timeCreated
) {
    public DataDeitalTicket(Ticket ticket, String qrCodeNumber, String author, String name_event, String timeCreated){
        this(
                ticket.getTicket_id(),
                name_event,
                qrCodeNumber,
                author,
                ticket.getDate_created(),
                timeCreated
        );
    }
}
