package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.event.*;
import apiBoschEsy.apiInSpringBoot.infra.exception.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.service.ImageService;
import apiBoschEsy.apiInSpringBoot.service.event.EventService;
import apiBoschEsy.apiInSpringBoot.service.utils.ImageHandler;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


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
    public ResponseEntity registerEvent(@ModelAttribute @Valid DataRegisterEvent dataRegisterEvent, UriComponentsBuilder uriBuilder) throws ExceptionDateInvalid {
        var event = eventService.createEvent(dataRegisterEvent);
        var uri = uriBuilder.path("/event/{id}").build(event.event_id());

        return ResponseEntity.created(uri).body(event);
    }
    // GET ALL Event
    @GetMapping("/events")
    public ResponseEntity listAllEvents(@PageableDefault(size = 10, sort = {"nameOfEvent"}) Pageable pageable){
        var list = eventService.getAllEvents(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    // Get By Id
    @GetMapping("/{event_id}")
    public ResponseEntity getEventById(@PathVariable Long event_id){
        var eventById = eventService.getEventById(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(eventById);
    }
    // GET (Return date_event, nameOfEvent, area and descriptions_event)
    @GetMapping("/card")
    public ResponseEntity getCardEvent(@PageableDefault(size = 3, sort = {"nameOfEvent"}) Pageable pageable){
        var card = eventService.cardReturn(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }
    // GET (Return name_event)
    @GetMapping("/name")
    public ResponseEntity getNameOfEvent(@PageableDefault(size = 10, sort = {"nameOfEvent"})Pageable pageable){
        var nameOfEvent = eventService.returnNameOfEvent(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(nameOfEvent);
    }
    // GET (Return name_event, date_event, hour_initial_start_event, local_event, description_event, hour_initial_and_finish_event, image_event)
    @GetMapping("/feed")
    public ResponseEntity getEventFeed(){
       var eventFeed = eventService.returnFeed();
       return ResponseEntity.status(HttpStatus.OK).body(eventFeed);
    }
    // GET Page Control Event (Return NameOfEvent, initial_date, initial_time, local, area, presença?) -> My Event
    @GetMapping("/myEvent")
    public ResponseEntity getMyEvents(@PageableDefault(size = 6, sort = {"nameOfEvent"}) Pageable pageable){
        var myEvents = eventService.returnMyEvents(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(myEvents);
    }

    // PUT Event
//    @PutMapping
//    @Transactional
//    public ResponseEntity changeInfoEvent(@RequestBody @Valid DataToUpdate data){
//        var event = repositoryEvent.getReferenceById(data.event_id());
//        event.toUpdateInfoEvent(data);
//        return ResponseEntity.status(HttpStatus.OK).body(new DataDetailEvent(event));
//    }

//    // DELETE Event
//    @DeleteMapping("/{event_id}")
//    @Transactional
//    public ResponseEntity deleteEvent(@PathVariable Long event_id){
//        var event  = repositoryEvent.getReferenceById(event_id);
//        event.delete();
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
