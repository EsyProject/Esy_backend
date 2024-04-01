package apiBoschEsy.apiInSpringBoot.dto.assessment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataRegisterAssessment(
        @NotBlank
        String name,

        String suggestion,
        @NotBlank
        String description_comment,
        @Pattern(regexp = "^[1-5]$")
        @NotBlank
        String assessment
) {
}
