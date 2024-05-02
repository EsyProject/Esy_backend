package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public record DataDeitalTicket(
        Long ticket_id,
        String event_name,
        String initialDate,
        String finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        String qrCodeNumber,
        String user,
        LocalDate date_created,
        LocalTime timeCreated
) {
    public DataDeitalTicket(Ticket ticket, String initialDate, String finishDate, String qrCodeNumber, String user, String name_event){
        this(
                ticket.getTicket_id(),
                name_event,
                initialDate,
                finishDate,
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket(),
                qrCodeNumber,
                user,
                ticket.getDate_created(),
                ticket.getTime_create()
        );
    }
}
