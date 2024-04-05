package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.event.DataDetailEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    private IRepositoryEvent repositoryEvent;

    // POST Event
    @PostMapping
    public ResponseEntity registekrEvent(DataRegisterEvent dataRegisterEvent, UriComponentsBuilder uriBuilder){
        var event = new Event(dataRegisterEvent);
        repositoryEvent.save(event);
        var uri = uriBuilder.path("/event/{id}").buildAndExpand(event.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailEvent(event));
    }
    // GET ALL Event

    // GET byId Event
    // PUT Event
    // DELETE Event

}
