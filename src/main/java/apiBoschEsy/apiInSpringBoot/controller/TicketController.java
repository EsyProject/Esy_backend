package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataListTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataTimeTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDateTicket;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.exception.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.ticket.TicketService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<DataDeitalTicket> registerTicket(@ModelAttribute @Valid DataRegisterTicket dataRegisterTicket,
                                                           UriComponentsBuilder uriBuilder,
                                                           @PathVariable Long event_id,
                                                           @AuthenticationPrincipal Jwt jwt
                                                           ) throws ExceptionDateInvalid {
        var ticket = ticketService.createTicket(dataRegisterTicket, event_id, jwt);
        var uri = uriBuilder.path("ticket/{id}").build(ticket.ticket_id());

        return ResponseEntity.created(uri).body(ticket);
    }

    // PACTH

//    @PatchMapping("/update")
//    public ResponseEntity<DataDeitalTicket> addImage(@RequestBody @Valid DataRegisterTicket dataRegisterTicket) throws ExceptionDateInvalid{
//
//        return ResponseEntity.status(HttpStatus.OK).body();
//    }

}
