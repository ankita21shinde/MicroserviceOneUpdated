package com.example.MicroserviceOne.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class HomeController {

    @Autowired
    private WebClient webClient;

    public HomeController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }


    @GetMapping("/save")
    public String getPost(){
        return "Festival -> Diwali , Dasara";
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
    }
