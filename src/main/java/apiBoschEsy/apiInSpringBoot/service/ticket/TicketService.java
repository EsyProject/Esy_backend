package apiBoschEsy.apiInSpringBoot.service.ticket;

import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.ticket.*;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.*;
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
    public DataDeitalTicket createTicket(@RequestBody @Valid DataRegisterTicket dataRegisterTicket, @PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws ExceptionDateInvalid, EventNotFoundException, CreateMoreTicketException, UserDontCreateTicket {

        // Date and Time
        LocalDate dateCurrent = formatService.getCurrentDate();
        String timeCurrent = formatService.getCurrentTimeFormatted();

        // Find Event by ID
        Event event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));

        // Creating a user
        String username = new DataAuth(jwt).userName();

        // Check if the event allows multiple tickets per user
        if (!event.isAllowMultipleTicketsPerUser()) {
            // Check if the user already has a ticket for this event
            boolean userHasTicket = repositoryTicket.existsByEventAndAuthor(event, username);
            if (userHasTicket) {
                throw new CreateMoreTicketException("This event already has a ticket. You cannot create more tickets for this event.");
            }
        }

        // Create the ticket
        Ticket ticket = new Ticket(dataRegisterTicket);
        if (!(ticket.getInitialDateTicket().isAfter(dateCurrent) || ticket.getInitialDateTicket().equals(dateCurrent))) {
            throw new ExceptionDateInvalid("Invalid date! You entered a date that has already passed. Enter a future or current date!");
        }

        // Set ticket details
        ticket.setDate_created(dateCurrent);
        ticket.setTime_create(LocalTime.parse(timeCurrent));
        ticket.setEvent(event);

        // Set the ticket ID to 1 if it's the first ticket, else auto-increment ID
        if (event.getTickets().isEmpty()) {
            ticket.setTicket_id(1L);
        } else {
            // Retrieve the maximum ticket ID and increment it by 1
            Long maxTicketId = event.getTickets().stream().mapToLong(Ticket::getTicket_id).max().orElse(0L);
            ticket.setTicket_id(maxTicketId + 1);
        }

        // Set the author of the ticket
        DataAuth user = new DataAuth(jwt);
        ticket.setAuthor(user.userName());

        // Generate the QRCode
        var qrCodeNumber = qrCode.generateRandomNumbers(7);
        ticket.setQrCodeNumber(qrCodeNumber);

        // Save the ticket
        repositoryTicket.save(ticket);

        // Return the ticket details
        return new DataDeitalTicket(ticket,
                formatService.formattedDate(ticket.getInitialDateTicket()),
                formatService.formattedDate(ticket.getFinishDateTicket()),
                qrCodeNumber,
                user.userName(),
                event.getNameOfEvent()
        );
    }

    @Transactional
    public DataDeitalUpdateTicket imageTicket(@ModelAttribute DataImageTicket dataImageTicket, @PathVariable Long event_id, @PathVariable Long ticket_id) throws TicketNotFoundException, EventNotFoundException {
        // Find event by ID
        Event event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + event_id));

        // Find ticket by ID
        Ticket ticket = event.getTickets().stream()
                .filter(t -> t.getTicket_id().equals(ticket_id))
                .findFirst()
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticket_id));

        List<MultipartFile> images = dataImageTicket.images();
        if (!(images == null) && !images.isEmpty()) {
            List<String> imageUrl = imageService.saveImagesTickets(ticket, images);
            ticket.setImageUrl(imageUrl);
        }

        return new DataDeitalUpdateTicket(ticket);
    }

    @Transactional
    public DataConfirmTicket confirmTicket(@PathVariable Long event_id, @PathVariable Long ticket_id) {
        // Find event
        var event = repositoryEvent.findById(event_id);

        if (event.isPresent()) {
            // Find ticket within the event
            var ticket = event.get().getTickets().stream()
                    .filter(t -> t.getTicket_id().equals(ticket_id))
                    .findFirst();

            if (ticket.isPresent()) {
                // Set isPresence attribute to true
                ticket.get().setIsPresence(true);

                // Save the updated ticket
                repositoryTicket.save(ticket.get());

                // Return the updated ticket
                return new DataConfirmTicket(ticket.get(), formatService.formattedDate(ticket.get().getDate_created()));
            }
        }
        // Return null if event or ticket is not found
        return null;
    }
}

