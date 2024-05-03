package apiBoschEsy.apiInSpringBoot.infra.error.exceptions;

public class TicketNotFoundException extends Throwable{
    public TicketNotFoundException(String message){
        super(message);
    }
}
