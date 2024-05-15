package apiBoschEsy.apiInSpringBoot.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterEmail(
        @NotBlank
        String ownerRef,
        @NotBlank
        String emailTo,
        @NotBlank
        String emailFrom,
        @NotBlank
        String title_email
) {
}
