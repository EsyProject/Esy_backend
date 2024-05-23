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

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    // Access the database
    @Autowired
    private IRepositoryTicket repositoryTicket;
    @Autowired
    private FormatService formatService;
    @Autowired
    private GenerateNumberQRCode qrCodeService;
    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private ImageService imageService;

    // Methods Controller

    // POST Register Ticket (ADMIN)
    @Transactional
    public DataDeitalRegisterTicket createTicketEventMain(@RequestBody @Valid DataRegisterTicket dataRegisterTicket, @PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws ExceptionDateInvalid, EventNotFoundException, NeedSamePeopleForCreate, OnlyOneTicket {
        // Date and Time
        LocalDate dateCurrent = formatService.getCurrentDate();
        String timeCurrent = formatService.getCurrentTimeFormatted();

        // Find Event by ID
        Event event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));

        // Creating a user
        String username = new DataAuth(jwt).userName();

        // Create the ticket
        if(!(event.getAuthor().equals(username))){
            throw new NeedSamePeopleForCreate("You don't created this event, so you cannot create ticket for event!");
        }
        // Check if a ticket already exists for this event
        if (event.getTickets() != null && !event.getTickets().isEmpty()) {
            throw new OnlyOneTicket("A ticket for this event already exists. Only one ticket can be created per event.");
        }
        Ticket ticket = new Ticket(dataRegisterTicket);
        ticket.setTicket_id(1L);
        if (!(ticket.getInitialDateTicket().isAfter(dateCurrent) || ticket.getInitialDateTicket().equals(dateCurrent))) {
            throw new ExceptionDateInvalid("Invalid date! You entered a date that has already passed. Enter a future or current date!");
        }

        // Set ticket details
        ticket.setDate_created(dateCurrent);
        ticket.setTime_create(LocalTime.parse(timeCurrent));
        ticket.setEvent(event);
        ticket.setAuthor(username);

        // Save the ticket
        repositoryTicket.save(ticket);

        return new DataDeitalRegisterTicket(
                formatService.formattedDate(ticket.getInitialDateTicket()),
                formatService.formattedDate(ticket.getFinishDateTicket()),
                ticket.getInitialTimeTicket(),
                ticket.getFinishTimeTicket(),
                username
        );
    }

    // POST (get ticket event (user default))
    @Transactional
    public DataDeitalTicket getTicketOfEvent(@PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws CreateMoreTicketException, EventNotFoundException {
        // Creating a user
        DataAuth user = new DataAuth(jwt);
        // DateTime and Attributes
        var currentDate = formatService.getCurrentDate();
        var currentTime = formatService.getCurrentTimeFormatted();
        // Get event
        Event event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event Not found with ID: " + event_id));
        // Get nameOfEvent
        var nameOfEvent = event.getNameOfEvent();
        // Get Tickets List<Tickets>
        List<Ticket> listTickets = event.getTickets();

        // Check if user already has a ticket
        boolean hasTicket = listTickets.stream()
                .anyMatch(ticket -> ticket.getAuthor().equals(user.userName()));
        if (hasTicket) {
            throw new CreateMoreTicketException("You already have a ticket for this event!");
        }

        // Creating a new ticket and Set attributes necessaries
        Ticket ticket = new Ticket();
        ticket.setAuthor(user.userName());
        ticket.setQrCodeNumber(qrCodeService.generateRandomNumbers(7));
        ticket.setDate_created(formatService.getCurrentDate());
        ticket.setTime_create(LocalTime.now());
        long nextTicketId = listTickets.size() + 1;
        ticket.setTicket_id(nextTicketId);
        ticket.setEvent(event);
        ticket.setIsPresence(false);
        repositoryTicket.save(ticket);

        return new DataDeitalTicket(
                ticket.getTicket_id(),
                nameOfEvent,
                ticket.getQrCodeNumber(),
                ticket.getAuthor(),
                ticket.getDate_created(),
                currentTime
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

