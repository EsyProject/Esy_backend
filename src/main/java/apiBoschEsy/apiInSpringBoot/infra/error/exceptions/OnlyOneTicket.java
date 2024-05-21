package apiBoschEsy.apiInSpringBoot.infra.error.exceptions;

public class OnlyOneTicket extends Throwable{
    public OnlyOneTicket(String message){
        super(message);
    }
}
