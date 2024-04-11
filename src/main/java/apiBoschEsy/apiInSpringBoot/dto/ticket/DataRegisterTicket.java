package apiBoschEsy.apiInSpringBoot.dto.ticket;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataRegisterTicket(
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate initialDateTicket,
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate finishDateTicket,
        @NotNull
        LocalTime initialTimeTicket,
        @NotNull
        LocalTime finishTimeTicket
) {
}
