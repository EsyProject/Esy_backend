package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.event.DataRegisterEvent;
import apiBoschEsy.apiInSpringBoot.event.Event;
import apiBoschEsy.apiInSpringBoot.event.IRepositoryEvent;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @GetMapping
    public String hello(){
        return "Hello World";
    }
    @Autowired
    private IRepositoryEvent repository; // Access the database

    @PostMapping
    @Transactional // Transactional active with database
    public void registerEvent(@RequestBody @Valid DataRegisterEvent data){
//        repository.save(new Event(data));
    }

}
