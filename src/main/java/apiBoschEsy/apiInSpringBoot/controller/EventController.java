package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.event.*;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.NameEventDuplicated;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.service.image.ImageService;
import apiBoschEsy.apiInSpringBoot.service.event.EventService;
import apiBoschEsy.apiInSpringBoot.service.utils.ImageHandler;
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

import java.util.Optional;
import java.util.stream.Stream;


@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private ImageHandler imageHandler;
    @Autowired
    private ImageService imageService;
    @Autowired
    private EventService eventService;

    // POST Event
    @PostMapping
    public ResponseEntity<DataDetailEvent> registerEvent(
            @ModelAttribute @Valid DataRegisterEvent dataRegisterEvent,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Jwt jwt
            ) throws ExceptionDateInvalid, NameEventDuplicated {
        var event = eventService.createEvent(dataRegisterEvent, jwt);
        var uri = uriBuilder.path("/event/{id}").build(event.event_id());

        return ResponseEntity.created(uri).body(event);
    }
    // GET ALL Event
    @GetMapping("/events")
    public ResponseEntity<Page<DataListEvent>> listAllEvents(@PageableDefault(size = 10, sort = {"nameOfEvent"}) Pageable pageable){
        var list = eventService.getAllEvents(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    // Get By Id
    @GetMapping("/{event_id}")
    public ResponseEntity<Optional> getEventById(@PathVariable Long event_id) throws EventNotFoundException {
        var eventById = eventService.getEventById(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(eventById);
    }
    // GET (Return date_event, nameOfEvent, area and descriptions_event)
    @GetMapping("/card")
    public ResponseEntity<Page<DataCardEvent>> getCardEvent(@PageableDefault(size = 3, sort = {"nameOfEvent"}) Pageable pageable){
        var card = eventService.cardReturn(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }
    // GET (Return name_event)
    @GetMapping("/name")
    public ResponseEntity<Page<DataNameEvent>> getNameOfEvent(@PageableDefault(size = 10, sort = {"nameOfEvent"})Pageable pageable){
        var nameOfEvent = eventService.returnNameOfEvent(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(nameOfEvent);
    }
    // GET (Return name_event, date_event, hour_initial_start_event, local_event, description_event, hour_initial_and_finish_event, image_event)
    @GetMapping("/feed")
    public ResponseEntity<Stream> getEventFeed(){
       var eventFeed = eventService.returnFeed();
       return ResponseEntity.status(HttpStatus.OK).body(eventFeed);
    }
    // GET Page Control Event (Return NameOfEvent, initial_date, initial_time, local, area, presença?) -> My Event
    @GetMapping("/myEvent")
    public ResponseEntity<Page<DataMyEvents>> getMyEvents(@PageableDefault(size = 6, sort = {"nameOfEvent"}) Pageable pageable){
        var myEvents = eventService.returnMyEvents(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(myEvents);
    }
}
