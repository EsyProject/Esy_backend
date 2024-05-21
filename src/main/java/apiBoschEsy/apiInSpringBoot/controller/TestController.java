package apiBoschEsy.apiInSpringBoot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {
    @GetMapping
    public String helloBruno(){
        return "Hello Bruno Gomes! Good ?";
    }
}
