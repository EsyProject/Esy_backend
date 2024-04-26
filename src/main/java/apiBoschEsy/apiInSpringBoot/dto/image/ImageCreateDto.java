package apiBoschEsy.apiInSpringBoot.dto.image;

import jakarta.validation.constraints.NotBlank;

public record ImageCreateDto(
        @NotBlank
        Long id,
        @NotBlank
        String imgUrl
) {
}
