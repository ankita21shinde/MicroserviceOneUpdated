package com.example.MicroserviceOne.controller;
import com.example.MicroserviceOne.entity.ServiceOneEntity;
import com.example.MicroserviceOne.repo.ServiceRepo;
import com.example.MicroserviceOne.service.ServiceTwoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class HomeController {
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private ServiceTwoImpl serviceTwo;
    @Autowired
    private WebClient webClient;
    public HomeController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

 //To Audit the query parameter
    @PostMapping("/create-service1")
    public ResponseEntity<String> create(@RequestParam (required = false)String name,@RequestParam int age) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Query parameter 'queryParameter' is required.");
        }
        // Create a new User entity
        ServiceOneEntity serviceOneEntity=new ServiceOneEntity();
       serviceOneEntity.setQueryParameter(name);
       // Return a success message
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/service-1")
    public String getPost(){
        return "Microservice 1 running successfully";
    }
}







