package apiBoschEsy.apiInSpringBoot.service.ticket;

import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.exception.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import apiBoschEsy.apiInSpringBoot.service.utils.GenerateNumberQRCode;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TicketService {

    // Access the database
    @Autowired
    private IRepositoryTicket repositoryTicket;
    @Autowired
    private FormatService formatService;
    @Autowired
    private GenerateNumberQRCode qrCode;
    @Autowired
    private IRepositoryEvent repositoryEvent;

    // Methods Controller

    // POST ticket
    @Transactional
    public DataDeitalTicket createTicket(@RequestBody @Valid DataRegisterTicket dataRegisterTicket, @PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws ExceptionDateInvalid {

        // Date and Time
        LocalDate dateCurrent = formatService.getCurrentDate();
        String timeCurrent = formatService.getCurrentTimeFormatted();

        // Find Event by ID
        var event = repositoryEvent.findById(event_id);
        var listTicketsOfEvent = event.get().getTickets();

        // Create the ticket
        Ticket ticket = new Ticket(dataRegisterTicket);
        listTicketsOfEvent.add(ticket);

        if (!(ticket.getInitialDateTicket().isAfter(dateCurrent) || ticket.getInitialDateTicket().equals(dateCurrent))) {
            throw new ExceptionDateInvalid("Invalid date! You entered a date that has already passed. Enter a future or current date!");
        }
        repositoryTicket.save(ticket);

        ticket.setDate_created(dateCurrent);
        ticket.setTime_create(LocalTime.parse(timeCurrent));


        // User
        DataAuth user = new DataAuth(jwt);

        // Generate the QRCode
        var qrCodeNumber = qrCode.generateRandomNumbers(7);


        return new DataDeitalTicket(ticket,
                formatService.formattedDate(ticket.getInitialDateTicket()),
                formatService.formattedDate(ticket.getFinishDateTicket()),
                qrCodeNumber,
                user.userName(),
                event.get().getNameOfEvent()
        );
    }
}
// PACTH the Tickets for set image
