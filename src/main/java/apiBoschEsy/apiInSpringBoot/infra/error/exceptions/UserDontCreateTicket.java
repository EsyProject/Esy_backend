package apiBoschEsy.apiInSpringBoot.infra.error.exceptions;

public class UserDontCreateTicket extends Throwable{
    public UserDontCreateTicket(String message){
        super(message);
    }
}
