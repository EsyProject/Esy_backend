package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;

public record DataTimeTicket(
        LocalDate initialDate,
        LocalDate finishDate
) {
    public DataTimeTicket(Ticket ticket){
        this(
                ticket.getInitialDate(),
                ticket.getFinishDate()
        );
    }
}
