package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    private IRepositoryEvent repositoryEvent;

    // POST Event

    // GET ALL Event
    // GET byId Event
    // PUT Event
    // DELETE Event

}
