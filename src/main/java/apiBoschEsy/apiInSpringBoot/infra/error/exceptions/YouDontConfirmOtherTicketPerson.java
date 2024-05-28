package apiBoschEsy.apiInSpringBoot.infra.error.exceptions;

public class YouDontConfirmOtherTicketPerson extends Throwable{
    public YouDontConfirmOtherTicketPerson(String message){
        super(message);
    }
}
