package apiBoschEsy.apiInSpringBoot.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterEmail(
        @NotBlank
        String ownerRef,
        @NotBlank
        String emailFrom,
        @NotBlank
        @Email
        String emailTo,
        @NotBlank
        String title_email,
        @NotBlank
        String body

) {
}