package apiBoschEsy.apiInSpringBoot.controller;

import apiBoschEsy.apiInSpringBoot.repository.IRepositoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private IRepositoryEvent repository; // Access the database

    @GetMapping
    public String hello(){
        return "Hello World";
    }

}
