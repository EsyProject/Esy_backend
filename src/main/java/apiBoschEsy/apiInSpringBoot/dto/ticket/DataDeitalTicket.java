package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataDeitalTicket(
        Long ticket_id,
        LocalDate initialDate,
        LocalDate finishDate,
        String initialTime,
        String finishTime,
        LocalDate date_created,
        LocalTime timeCreated,
        String qrCodeNumber
) {
    public DataDeitalTicket(Ticket ticket, String initialDate, String finishDate, String qrCodeNumber){
        this(
                ticket.getTicket_id(),
                ticket.getInitialDateTicket(),
                ticket.getFinishDateTicket(),
                initialDate,
                finishDate,
                ticket.getDate_created(),
                ticket.getTime_create(),
                qrCodeNumber
        );
    }
}
