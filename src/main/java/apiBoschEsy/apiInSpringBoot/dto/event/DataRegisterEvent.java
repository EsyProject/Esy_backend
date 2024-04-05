package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.place.Area;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record DataRegisterEvent(
        @NotBlank
        String nameOfEvent,
        Area responsible_area,
        Area access_area,
        String description_event,
        MultipartFile banner,


) {


}
