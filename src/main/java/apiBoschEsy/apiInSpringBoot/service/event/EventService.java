package apiBoschEsy.apiInSpringBoot.service.event;

import apiBoschEsy.apiInSpringBoot.dto.event.*;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.infra.exception.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryImage;
import apiBoschEsy.apiInSpringBoot.service.ImageService;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EventService {

    @Autowired
    private IRepositoryEvent repositoryEvent;
    @Autowired
    private IRepositoryImage image;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FormatService formatService;

    // Method POST
    @Transactional
    public DataDetailEvent createEvent(DataRegisterEvent dataRegisterEvent) throws ExceptionDateInvalid {

        String timeCurrent = formatService.getCurrentTimeFormatted();
        LocalDate dateCurrent = formatService.getCurrentDate();
        Event event = new Event(dataRegisterEvent);

        if(event.getInitial_date().isAfter(dateCurrent) || event.getInitial_date().equals(dateCurrent)){
            repositoryEvent.save(event);
        }else {
            throw new ExceptionDateInvalid("Invalid date! You entered a date that has already passed. Enter a future or current date!");
        }

        List<MultipartFile> images = dataRegisterEvent.images();
        if(!(images == null) && !images.isEmpty()){
            List<String> imageUrl = imageService.saveImages(event, images);
            event.setImgUrl(imageUrl);
        }

        event.setTime_created(LocalTime.parse(timeCurrent));
        event.setDateCreated(dateCurrent);


        return new DataDetailEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date()));
    }

    // Method GET all Events
    public Page getAllEvents(@PageableDefault(size = 10, sort = {"nameOfEvent"})Pageable pageable){
            var events = repositoryEvent.findAll(pageable);
            return events.map(event -> new DataListEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date())));
    }

    // Method GET by Id
    public Optional getEventById(@PathVariable Long event_id){
        var events = repositoryEvent.findById(event_id);
        return events.map(event -> new DataDetailEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date())));
    }

    // Method GET card
    public Page cardReturn(@PageableDefault(size = 3, sort = {"nameOfEvent"}) Pageable pageable){
        var events = repositoryEvent.findAll(pageable);
        return events.map(event -> new DataCardEvent(event, formatService.formattedDate(event.getInitial_date())));
    }

    // Method GET name Event
    public Page returnNameOfEvent(@PageableDefault(size = 10, sort = {"nameOfEvent"}) Pageable pageable){
        var nameOfEvent = repositoryEvent.findAll(pageable).map(DataNameEvent::new);
        return nameOfEvent;
    }

    // Method GET (Return name_event, date_event, hour_initial_start_event, local_event, description_event, hour_initial_and_finish_event, image_event)
    public Stream returnFeed(){
        var events = repositoryEvent.findAll().stream();
        return events.map(event -> new DataEventFeed(event, formatService.formattedDate(event.getInitial_date())));
    }

    // Method GET My Events
    public Page returnMyEvents(@PageableDefault(size = 6, sort = {"nameOfEvent"})Pageable pageable){
        var events = repositoryEvent.findAll(pageable);
        return events.map(event -> new DataMyEvents(event, formatService.formattedDate(event.getInitial_date())));
    }

    // PUT
    // DELETE

}