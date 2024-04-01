package apiBoschEsy.apiInSpringBoot.dto.event;

import apiBoschEsy.apiInSpringBoot.place.Place;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.Category;

import java.util.Locale;

public record DataRegisterEvent(
        @NotBlank
        String nameOfEvent,
        @NotBlank
        String category,
        @NotNull
        Place place,
        @NotBlank
        String data,
        @NotBlank
        String hourEvent,
        @NotNull
        String responsible,
        @NotNull
        Place costPlace,
        @NotBlank
        String description,
        @NotNull
        String duration,
        @NotBlank
        String imageURL,
        @NotNull
        Place participatingAreas,
        @NotNull
        @Pattern(regexp = "\\d{2,8}")
        String numberOfParticipants
) {
}
