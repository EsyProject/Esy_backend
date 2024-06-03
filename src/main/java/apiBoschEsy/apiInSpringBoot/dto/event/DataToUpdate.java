package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record DataToUpdate(
        @NotNull
        Area responsible_area,
        @NotBlank
        String description,
        @NotNull
        Place localEvent,
        @NotNull
        @DateTimeFormat( iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate initialDate,
        @NotNull
        @DateTimeFormat( iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate finishDate,
        @NotNull
        LocalTime initialTime,
        @NotNull
        LocalTime finishTime
) {
}
