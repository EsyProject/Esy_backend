package apiBoschEsy.apiInSpringBoot.service.ticket;

import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalUpdateTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataImageTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.TicketNotFoundException;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.image.ImageService;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import apiBoschEsy.apiInSpringBoot.service.utils.GenerateNumberQRCode;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

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
    @Autowired
    private ImageService imageService;

    // Methods Controller

    // POST ticket
    @Transactional
    public DataDeitalTicket createTicket(@RequestBody @Valid DataRegisterTicket dataRegisterTicket, @PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws ExceptionDateInvalid, EventNotFoundException {

        // Date and Time
        LocalDate dateCurrent = formatService.getCurrentDate();
        String timeCurrent = formatService.getCurrentTimeFormatted();

        // Find Event by ID
        Event event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));
        // Verify the event has a ticket
        var listTicketsOfEvent = event.getTickets();
        if(!listTicketsOfEvent.isEmpty()){
            throw new RuntimeException("That event has a ticket! Can't create more ticket for event");
        }

        // Create the ticket
        Ticket ticket = new Ticket(dataRegisterTicket);

        if (!(ticket.getInitialDateTicket().isAfter(dateCurrent) || ticket.getInitialDateTicket().equals(dateCurrent))) {
            throw new ExceptionDateInvalid("Invalid date! You entered a date that has already passed. Enter a future or current date!");
        }
        repositoryTicket.save(ticket);

        ticket.setDate_created(dateCurrent);
        ticket.setTime_create(LocalTime.parse(timeCurrent));
        ticket.setEvent(event);


        // User
        DataAuth user = new DataAuth(jwt);
        ticket.setAuthor(user.userName());

        // Generate the QRCode
        var qrCodeNumber = qrCode.generateRandomNumbers(7);
        ticket.setQrCodeNumber(qrCodeNumber);


        return new DataDeitalTicket(ticket,
                formatService.formattedDate(ticket.getInitialDateTicket()),
                formatService.formattedDate(ticket.getFinishDateTicket()),
                qrCodeNumber,
                user.userName(),
                event.getNameOfEvent()
        );
    }

    @Transactional
    public DataDeitalUpdateTicket imageTicket(@ModelAttribute DataImageTicket dataImageTicket, @PathVariable Long ticket_id) throws TicketNotFoundException {
        // Find ticket_ID
        Ticket ticket = repositoryTicket.findById(ticket_id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticket_id));

        List<MultipartFile> images = dataImageTicket.images();
        if(!(images == null) && !images.isEmpty()){
            List<String> imageUrl = imageService.saveImagesTickets(ticket, images);
            ticket.setImageUrl(imageUrl);
        }

        return new DataDeitalUpdateTicket(ticket);
    }
}

