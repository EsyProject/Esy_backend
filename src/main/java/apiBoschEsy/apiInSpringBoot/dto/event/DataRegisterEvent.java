package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.constants.Area;
import apiBoschEsy.apiInSpringBoot.constants.Place;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataRegisterEvent(
        @NotBlank
        String nameOfEvent,
        @NotNull
        Area responsible_area,
        @NotNull
        Area access_event,
        @NotBlank
        String description,
        @JsonAlias({"image"})
        List<MultipartFile> images,
        @NotNull
        Place localEvent,
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate initialDate,
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate finishDate,
        @NotNull
        LocalTime initialTime,
        @NotNull
        LocalTime finishTime
) {

}
