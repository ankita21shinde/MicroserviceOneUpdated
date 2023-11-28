package com.example.MicroserviceOne.controller;


//import com.example.MicroserviceOne.entity.ServiceOneDto;
import com.example.MicroserviceOne.entity.ServiceOneEntity;
import com.example.MicroserviceOne.repo.ServiceRepo;
import com.example.MicroserviceOne.service.ServiceTwoImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
//@Slf4j
public class HomeController {


//    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
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
    public ResponseEntity<String> create(@RequestParam (required = false)String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Query parameter 'queryParameter' is required.");
        }
//        ServiceOneEntity savedEntity = serviceTwo.save(serviceOneDto);
//      ServiceOneDto savedEntityDTO = serviceTwo.convertEntityToDTO(savedEntity);


        // Create a new User entity
        ServiceOneEntity serviceOneEntity=new ServiceOneEntity();
       serviceOneEntity.setQueryParameter(name);

       // Save the user to the database
//        serviceRepo.save(serviceOneEntity);

        // Return a success message
        return ResponseEntity.ok("User created successfully");
    }
    @GetMapping("/service-1")
    public String getPost(){
//        double doubleValue = Double.parseDouble("stringValue");
//        logger.info("In service -1");
        return "Microservice 1 running successfully";

    }
}




//    public Mono<String> getMessage () {
//            // Simulate processing to retrieve a message (replace this with your actual logic)
//            String retrievedMessage = "Hello, this is the retrieved message!";
//
//            // Audit information
//            AuditData auditData = new AuditData("Microservice", "/get-message", "GET", null, retrievedMessage);
//
//            // Send audit data to central auditing service
//            return webClient.post()
//                    .uri("/api/audit")
//                    .bodyValue(auditData)
//                    .retrieve()
//                    .bodyToMono(String.class);
//        }



//    @PostMapping("/process-request")
//    public Mono<String> processRequest(@RequestBody RequestData requestData) {
//        // Process the request in Microservice 1
//        // ...

// Audit information

//        AuditData auditData = new AuditData("Microservice 1", "/process-request", "POST", requestData, null);
//
//        // Send audit data to central auditing service
//        return webClient.post()
//                .uri("/api/audit")
//                .bodyValue(auditData)
//                .retrieve()
//                .bodyToMono(String.class);
//    }