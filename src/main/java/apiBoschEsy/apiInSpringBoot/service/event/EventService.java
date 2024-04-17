package apiBoschEsy.apiInSpringBoot.service.event;

import apiBoschEsy.apiInSpringBoot.dto.event.DataCardEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataDetailEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataListEvent;
import apiBoschEsy.apiInSpringBoot.dto.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.infra.exception.ExceptionDateInvalid;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryImage;
import apiBoschEsy.apiInSpringBoot.service.ImageService;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
    public DataDetailEvent dataDetailEvent(DataRegisterEvent dataRegisterEvent) throws ExceptionDateInvalid {
        FormatService formatService = new FormatService();

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
    public Page<DataListEvent> getAllEvents(@PageableDefault(size = 10, sort = {"nameOfEvent"})Pageable pageable){
        var list = repositoryEvent.findAll(pageable).map(DataListEvent::new);
        return list;
    }

    // Method GET by Id
    public Event getEventById(@PathVariable Long event_id){
        return repositoryEvent.findById(event_id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + event_id));
    }

    // Method GET card
    public Page<DataCardEvent> cardReturn(@PageableDefault(size = 3, sort = {"nameOfEvent"}) Pageable pageable){
        var cardOfEvent = repositoryEvent.findAll(pageable).map(DataCardEvent::new);
        return cardOfEvent;
    }


}
