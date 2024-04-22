package apiBoschEsy.apiInSpringBoot.dto.assessment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataRegisterAssessment(
        @NotBlank
        String name,

        String suggestion,
        @NotBlank
        String description_comment,
        @Min(value = 1, message = "The minimum value is 1")
        @Max(value = 5, message = "The maximum value is 5")
        Integer assessment
) {
}
