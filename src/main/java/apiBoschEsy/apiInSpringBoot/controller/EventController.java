package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.event.*;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.service.ImageService;
import apiBoschEsy.apiInSpringBoot.service.utils.ImageHandler;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.crypto.Data;
import java.util.List;


@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private ImageHandler imageHandler;
    @Autowired
    private ImageService imageService;

    // POST Event
    @PostMapping
    @Transactional
    public ResponseEntity registerEvent(@ModelAttribute @Valid DataRegisterEvent dataRegisterEvent, UriComponentsBuilder uriBuilder){
        var event = new Event(dataRegisterEvent);
        List<MultipartFile> images = dataRegisterEvent.images();

        if(!(images == null) && !images.isEmpty()){
            List<String> imageUrl = imageService.saveImages(event, images);
            event.setImgUrl(imageUrl);
        }
        repositoryEvent.save(event);
        var uri = uriBuilder.path("/event/{id}").buildAndExpand(event.getEvent_id()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailEvent(event));
    }
    // GET ALL Event
    @GetMapping("/events")
    public ResponseEntity<Page<DataListEvent>> listAllEvents(@PageableDefault(size = 10, sort = {"nameOfEvent"}) Pageable pageable){
        var list = repositoryEvent.findAll(pageable).map(DataListEvent::new);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    // Get By Id
    @GetMapping("/{event_id}")
    public ResponseEntity getEventById(@PathVariable Long event_id){
        var eventById = repositoryEvent.findById(event_id);
        if(eventById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found this event!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventById.get());
    }
    // GET (Return date_event, nameOfEvent, area and descriptions_event)
    @GetMapping("/card")
    public ResponseEntity<Page<DataCardEvent>> getCardEvent(@PageableDefault(size = 3, sort = {"nameOfEvent"}) Pageable pageable){
        var cardEvent = repositoryEvent.findAll(pageable).map(DataCardEvent::new);
        return ResponseEntity.status(HttpStatus.OK).body(cardEvent);
    }
    // GET (Return name_event)
    @GetMapping("/name")
    public ResponseEntity getNameOfEvent(){
        var nameOfEvent = repositoryEvent.findAll().stream().map(DataNameEvent::new);
        return ResponseEntity.status(HttpStatus.OK).body(nameOfEvent);
    }
    // GET (Return name_event, date_event, hour_initial_start_event, local_event, description_event, hour_initial_and_finish_event, image_event)
    @GetMapping("/feed")
    public ResponseEntity getEventFeed(){
        var eventFeed = repositoryEvent.findAll().stream().map(DataEventFeed::new);
        return ResponseEntity.status(HttpStatus.OK).body(eventFeed);
    }
    // GET Page Control Event (Return NameOfEvent, initial_date, initial_time, local, area, presença?)
    @GetMapping("/myEvent")
    public ResponseEntity getMyEvents(){
        var my_event = repositoryEvent.findAll().stream().map(DataMyEvents::new);
        return ResponseEntity.status(HttpStatus.OK).body(my_event);
    }
    // PUT Event
    @PutMapping
    @Transactional
    public ResponseEntity changeInfoEvent(@RequestBody @Valid DataToUpdate data){
        var event = repositoryEvent.getReferenceById(data.event_id());
        event.toUpdateInfoEvent(data);
        return ResponseEntity.status(HttpStatus.OK).body(new DataDetailEvent(event));
    }

    // DELETE Event
    @DeleteMapping("/{event_id}")
    @Transactional
    public ResponseEntity deleteEvent(@PathVariable Long event_id){
        var event  = repositoryEvent.getReferenceById(event_id);
        event.delete();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
