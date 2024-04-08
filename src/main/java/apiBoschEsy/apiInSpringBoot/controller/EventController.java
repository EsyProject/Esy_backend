package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.dto.event.DataDetailEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.service.ImageService;
import apiBoschEsy.apiInSpringBoot.service.utils.ImageHandler;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity registekrEvent(@ModelAttribute @Valid DataRegisterEvent dataRegisterEvent, UriComponentsBuilder uriBuilder){
        var event = new Event(dataRegisterEvent);
        List<MultipartFile> images = dataRegisterEvent.images();

        if(!(images == null) && !images.isEmpty()){
            List<String> imageUrl = imageService.saveImages(event, images);
            event.setImageUrl(imageUrl);
        }
        repositoryEvent.save(event);
        var uri = uriBuilder.path("/event/{id}").buildAndExpand(event.getEvent_id()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailEvent(event));
    }
    // GET ALL Event
//    @GetMapping("/events")
//    public ResponseEntity listAllEvent(DataListEvent dataListEvent){
//        var event = repositoryEvent.findAll(dataListEvent).stream().map(DataListEvent::new);
//        return ResponseEntity.ok(event);
//    }
    // GET (Return date_event, nameOfEvent, area and descriptions_event)
    // GET (Return name_event)
    // GET (Return Pageable 3)
    // GET (Return name_event, date_event, hour_initial_start_event, local_event, description_event, hour_initial_and_finish_event, image_event)
    // PUT Event
    // DELETE Event

}
