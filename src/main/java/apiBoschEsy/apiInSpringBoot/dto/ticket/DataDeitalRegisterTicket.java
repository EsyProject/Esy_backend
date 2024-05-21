package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalTime;

public record DataDeitalRegisterTicket(
        String initialDate,
        String finishDate,
        LocalTime initialTime,
        LocalTime finishTime
) {
    public DataDeitalRegisterTicket(String initialDate, String finishDate, Ticket ticket){
        this(
                initialDate,
                finishDate,
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket()
        );
    }
}
