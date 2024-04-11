package apiBoschEsy.apiInSpringBoot.dto.ticket;

import apiBoschEsy.apiInSpringBoot.entity.Ticket;

import java.time.LocalDate;

public record DataDateTicket(
        Long id,
        LocalDate initialDate,
        LocalDate finishDate
) {
    public DataDateTicket(Ticket ticket){
        this(
                ticket.getTicket_id(),
                ticket.getInitialDateTicket(),
                ticket.getFinishDateTicket()
        );
    }
}
