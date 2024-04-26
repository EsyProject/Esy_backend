package apiBoschEsy.apiInSpringBoot.dto.image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImageDetailDto(
        @NotNull
        Long id,
        @NotBlank
        Long event_id,
        @NotBlank
        String imgUrl
) {
}
