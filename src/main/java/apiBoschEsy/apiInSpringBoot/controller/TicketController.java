package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.ticket.*;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.*;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.ticket.TicketService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    // Autowired injection dependencies
    @Autowired
    private IRepositoryTicket iRepositoryTicket;
    @Autowired
    private TicketService ticketService;

    // POST Ticket
    @PostMapping("/{event_id}")
    public ResponseEntity<DataDeitalRegisterTicket> registerTicket(
            @RequestBody @Valid DataRegisterTicket dataRegisterTicket,
            UriComponentsBuilder uriBuilder,
            @PathVariable Long event_id,
            @AuthenticationPrincipal Jwt jwt
    ) throws ExceptionDateInvalid, EventNotFoundException, CreateMoreTicketException, UserDontCreateTicket, NeedSamePeopleForCreate, OnlyOneTicket {
        var ticket = ticketService.createTicketEventMain(dataRegisterTicket, event_id, jwt);
//        var uri = uriBuilder.path("ticket/{id}").build(ticket.ticket_id());

//        return ResponseEntity.created(uri).body(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PostMapping("/getTicket/{event_id}")
    public ResponseEntity<DataDeitalTicket> getTicketPerUser(@PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws ExceptionDateInvalid, CreateMoreTicketException, EventNotFoundException, UserDontCreateTicket {
        var ticketUser = ticketService.getTicketOfEvent(event_id, jwt);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketUser);
    }

    // PATCH

    @PatchMapping("/{event_id}/{ticket_id}")
    public ResponseEntity<DataDeitalUpdateTicket> updateImageTicket(
            @ModelAttribute @Valid DataImageTicket dataImageTicket,
            @PathVariable Long event_id,
            @PathVariable Long ticket_id
    ) throws TicketNotFoundException, EventNotFoundException {
        var imageTicket = ticketService.imageTicket(dataImageTicket, event_id, ticket_id);
        return ResponseEntity.status(HttpStatus.OK).body(imageTicket);
    }

    @PatchMapping("/{event_id}/{ticket_id}/confirm")
    public ResponseEntity<DataConfirmTicket> confirmTicket(
            @PathVariable Long event_id,
            @PathVariable Long ticket_id
    ){
        var confirm = ticketService.confirmTicket(event_id, ticket_id);
        return ResponseEntity.status(HttpStatus.OK).body(confirm);
    }
}