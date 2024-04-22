package apiBoschEsy.apiInSpringBoot.service.ticket;

import apiBoschEsy.apiInSpringBoot.dto.ticket.DataDeitalTicket;
import apiBoschEsy.apiInSpringBoot.dto.ticket.DataRegisterTicket;
import apiBoschEsy.apiInSpringBoot.entity.Ticket;
import apiBoschEsy.apiInSpringBoot.infra.exception.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryUser;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TicketService {

    // Access the database
    @Autowired
    private IRepositoryTicket repositoryTicket;
    @Autowired
    private IRepositoryUser repositoryUser;
    @Autowired
    private FormatService formatService;

    // Methods Controller

    // POST ticket
    @Transactional
    public DataDeitalTicket createTicket(@RequestBody @Valid DataRegisterTicket dataRegisterTicket) throws ExceptionDateInvalid {
        // Date and time formatted
        var dateCurrent = formatService.getCurrentDate();
        String timeCurrent = formatService.getCurrentTimeFormatted();

        // Creating a ticket
        var ticket = new Ticket(dataRegisterTicket);

        if (!(ticket.getInitialDateTicket().isAfter(dateCurrent) || ticket.getInitialDateTicket().equals(dateCurrent))) {
            throw new ExceptionDateInvalid("Invalid date! You entered a date that has already passed. Enter a future or current date!");
        }
        repositoryTicket.save(ticket);

        ticket.setDate_created(dateCurrent);
        ticket.setTime_create(LocalTime.parse(timeCurrent));

        return new DataDeitalTicket(ticket, formatService.formattedDate(ticket.getInitialDateTicket()), formatService.formattedDate(ticket.getFinishDateTicket()));
    }




}
