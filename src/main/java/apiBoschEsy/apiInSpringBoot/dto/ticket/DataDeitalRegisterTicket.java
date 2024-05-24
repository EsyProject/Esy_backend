package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalTime;

public record DataDeitalRegisterTicket(
        String initialDateTicket,
        String finishDateTicket,
        LocalTime initialTimeTicket,
        LocalTime finishTimeTicket,
        String authorTicket
) {
    public DataDeitalRegisterTicket(String initialDateTicket, String finishDateTicket, Ticket ticket, String authorTicket){
        this(
                initialDateTicket,
                finishDateTicket,
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket(),
                authorTicket
        );
    }
}
