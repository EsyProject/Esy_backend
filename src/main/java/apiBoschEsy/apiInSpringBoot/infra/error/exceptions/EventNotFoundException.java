package apiBoschEsy.apiInSpringBoot.infra.error.exceptions;

public class EventNotFoundException extends Throwable{
    public EventNotFoundException(String message){
        super(message);
    }
}
