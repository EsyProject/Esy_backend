package apiBoschEsy.apiInSpringBoot.service.event;

import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.dto.event.*;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.EventNotFoundException;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.infra.error.exceptions.NameEventDuplicated;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryImage;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryTicket;
import apiBoschEsy.apiInSpringBoot.service.image.ImageService;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import apiBoschEsy.apiInSpringBoot.service.utils.GenerateNumberQRCode;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private IRepositoryTicket repositoryTicket;
    @Autowired
    private IRepositoryImage image;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FormatService formatService;
    @Autowired
    private GenerateNumberQRCode generateNumberQRCode;

    // Method POST
    @Transactional
    public DataDetailEvent createEvent(@ModelAttribute @Valid DataRegisterEvent dataRegisterEvent, @AuthenticationPrincipal Jwt jwt) throws ExceptionDateInvalid, NameEventDuplicated {
        DataAuth user = new DataAuth(jwt);
        String timeCurrent = formatService.getCurrentTimeFormatted();
        LocalDate dateCurrent = formatService.getCurrentDate();
        // Creating a instance
        Event event = new Event(dataRegisterEvent);
        // Validation
        if (!(event.getInitial_date().isAfter(dateCurrent) || event.getInitial_date().equals(dateCurrent))) {
            throw new ExceptionDateInvalid("Data Inválida!");
        }
//        if (!(event.getInitial_time().isAfter(event.getFinish_time()) || event.getFinish_time().isBefore(event.getInitial_time()))) {
//                throw new ExceptionDateInvalid("Horário Inválida!");
//        }
        // Valid nameOfEvent already exist
        Optional <Event> nameOfEventAlreadyExist = repositoryEvent.findEventByNameOfEvent(dataRegisterEvent.nameOfEvent());
        if(nameOfEventAlreadyExist.isPresent()){
            throw new NameEventDuplicated("Evento Existente");
        }
        repositoryEvent.save(event);
        List<MultipartFile> images = dataRegisterEvent.images();
        if(!(images == null) && !images.isEmpty()){
            List<String> imageUrl = imageService.saveImages(event, images);
            event.setImgUrl(imageUrl);
        }
        event.setTime_created(LocalTime.parse(timeCurrent));
        event.setDateCreated(dateCurrent);
        event.setAuthor(user.userName());
        return new DataDetailEvent(
                event,
                formatService.formattedDate(event.getInitial_date()),
                formatService.formattedDate(event.getFinish_date()),
                user.userName()
        );
    }

    // Method GET all Events
    public Page getAllEvents(@PageableDefault(size = 10, sort = {"nameOfEvent"})Pageable pageable){
            var events = repositoryEvent.findAll(pageable);
            return events.map(event -> new DataListEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date())));
    }

    // Method GET by Id
    public Optional getEventById(@PathVariable Long event_id) throws EventNotFoundException {
        var events = repositoryEvent.findById(event_id);
        if(events.isEmpty()){
            throw new EventNotFoundException("Event not Found");
        }
        return events.map(event -> new DataDetailEvent(event, formatService.formattedDate(event.getInitial_date()), formatService.formattedDate(event.getFinish_date()), event.getAuthor()));
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

    // Method GET My Events per user
    public Stream<DataMyEvents> myEvents(@AuthenticationPrincipal Jwt jwt){
        // Access the all events
        var events = repositoryEvent.findAll();

        // Creating a user
        DataAuth user = new DataAuth(jwt);

        return events.stream()
                .filter(event -> event.getAuthor().equals(user.userName()))
                .map(event -> new DataMyEvents(
                        event.getEvent_id(),
                        event.getNameOfEvent(),
                        formatService.formattedDate(event.getInitial_date()),
                        event.getInitial_time(),
                        event.getLocalEvent(),
                        event.getResponsible_area()
                ));
    }

    // PUT the event
    @Transactional
    public DataDetailUpdateEvent updateEvent(@ModelAttribute @Valid DataToUpdate dataToUpdate, @PathVariable Long event_id, @AuthenticationPrincipal Jwt jwt) throws EventNotFoundException {

        //Find the event by ID
        Event event = repositoryEvent.findById(event_id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + event_id));

        // User
        DataAuth user = new DataAuth(jwt);

        event.updateTheInformationsOfEvent(dataToUpdate);

        return new DataDetailUpdateEvent(
                event,
                formatService.formattedDate(event.getInitial_date()),
                formatService.formattedDate(event.getFinish_date()),
                user.userName()
                );
    }

    // DELETE

}
