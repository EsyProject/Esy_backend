package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataListTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataTimeTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDateTicket;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

//        return ResponseEntity.status(HttpStatus.CREATED).body(new DataDeitalTicket(ticket));
        return ResponseEntity.created(uri).body(new DataDeitalTicket(ticket));
    }
    @GetMapping("/tickets")
    public ResponseEntity<Page<DataListTicket>> listTicket(@PageableDefault(size = 10) Pageable pageable){
        var list = iRepositoryTicket.findAll(pageable).map(DataListTicket::new);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/time")
    public ResponseEntity timeTicket(){
        var timeTicket = iRepositoryTicket.findAll().stream().map(DataTimeTicket::new);
        return ResponseEntity.status(HttpStatus.OK).body(timeTicket);
    }
    @GetMapping("/date")
    public ResponseEntity dateTicket(){
        var dateTicket = iRepositoryTicket.findAll().stream().map(DataDateTicket::new);
        return ResponseEntity.status(HttpStatus.OK).body(dateTicket);
    }
    @GetMapping("/{id}")
    public ResponseEntity ticketById(@PathVariable Long id){
        var ticketId = iRepositoryTicket.findById(id);
        if(ticketId.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Not Found the ticket");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticketId.get());
    }
}