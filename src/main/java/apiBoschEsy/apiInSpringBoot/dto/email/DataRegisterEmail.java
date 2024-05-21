package apiBoschEsy.apiInSpringBoot.dto.email;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterEmail(
        @NotBlank
        String emailTo,
        @NotBlank
        String emailFrom
) {
}
