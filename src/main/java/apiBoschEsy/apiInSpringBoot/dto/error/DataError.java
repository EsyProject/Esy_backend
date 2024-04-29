package apiBoschEsy.apiInSpringBoot.dto.error;

import org.springframework.validation.FieldError;

public record DataError(
        String message
) {
    public DataError(FieldError error){
        this(
                error.getDefaultMessage()
        );
    }
}
