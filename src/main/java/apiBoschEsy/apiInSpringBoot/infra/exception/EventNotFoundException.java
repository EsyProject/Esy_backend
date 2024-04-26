package apiBoschEsy.apiInSpringBoot.infra.exception;

public class EventNotFoundException extends Throwable{
    public EventNotFoundException(String message){
        super(message);
    }
}
