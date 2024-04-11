package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private IRepositoryTicket iRepositoryTicket;


    @PostMapping
    @Transactional
    public ResponseEntity registerTicket(@RequestBody @Valid DataRegisterTicket dataRegisterTIcket, UriComponentsBuilder uriBuilder){
        var ticket = new Ticket(dataRegisterTIcket);
        iRepositoryTicket.save(ticket);

        var uri = uriBuilder.path("/ticket/{ticket_id}").buildAndExpand(ticket.getTicket_id()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(new DataDeitalTicket(ticket));
    }
}
