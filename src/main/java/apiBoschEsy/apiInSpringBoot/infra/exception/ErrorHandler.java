package apiBoschEsy.apiInSpringBoot.infra.exception;

import apiBoschEsy.apiInSpringBoot.dto.error.DataError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.crypto.Data;

@RestControllerAdvice // Annotation for class of errosHandler
public class ErrorHandler {
    // For which Exception this method is work
    // ERROR 404 -> Not Found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerError404(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    // ERROR 400 - > ERROR by Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerError400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors(); // Get list of Erros
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().map(DataError::new).toList()); // (errors.stream().map()) -> This convert field error for DataErrorValidation
    }
    @ExceptionHandler(ExceptionDateInvalid.class)
    public ResponseEntity handlerErrorDate400(ExceptionDateInvalid exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataError(exception.getMessage()));
    }
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handlerErrorEvent404(EventNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataError(exception.getMessage()));
    }
}