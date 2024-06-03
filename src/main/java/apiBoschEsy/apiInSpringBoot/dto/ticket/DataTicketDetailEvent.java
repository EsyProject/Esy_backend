package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.util.List;

public record DataTicketDetailEvent(
        Long ticket_id,
        String initial_date,
        String nameOfEvent,
        String qrCodeTicket,
        Area responsible_area,
        List<String> imageUrl
) {
    public DataTicketDetailEvent(String date_event, Event event, Ticket ticket){
        this(
                ticket.getTicket_id(),
                date_event,
                event.getNameOfEvent(),
                ticket.getQrCodeNumber(),
                event.getResponsible_area(),
                ticket.getImageUrl()
        );
    }
}
