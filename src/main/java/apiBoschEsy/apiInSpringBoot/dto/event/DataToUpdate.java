package apiBoschEsy.apiInSpringBoot.dto.event;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataToUpdate(
        @NotNull
        Long event_id,
        String description,
        List<String> imgUrl,
        LocalDate initialDate,
        LocalDate finishDate,
        LocalTime initialTime,
        LocalTime finishTime,
        LocalDate initialDateTicket,
        LocalDate finishDateTicket,
        LocalTime initialTimeTicket,
        LocalTime finishTimeTicket
) {
}
