package apiBoschEsy.apiInSpringBoot.dto.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataRegisterTicket(
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate initialDateTicket,
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate finishDateTicket,
        @NotNull
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime initialTimeTicket,
        @NotNull
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime finishTimeTicket
) {
}
