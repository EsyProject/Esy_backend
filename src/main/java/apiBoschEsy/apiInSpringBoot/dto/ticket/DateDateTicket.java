package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;

public record DateDateTicket(
        LocalDate initialDate,
        LocalDate finishDate
) {
    public DateDateTicket(Ticket ticket){
        this(
                ticket.getInitialDate(),
                ticket.getFinishDate()
        );
    }
}
