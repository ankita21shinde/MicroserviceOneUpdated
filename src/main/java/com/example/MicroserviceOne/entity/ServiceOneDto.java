//package com.example.MicroserviceOne.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@Entity
//
//public class ServiceOneDto {
//    private String queryParameter;
//    private LocalDateTime auditTime;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private long id;
//
//    private String serviceName="Microservice 1";
//
//    //extra fields
//    private String requestTime;
//    private String responseTime;
//    private int StatusCode;
//    private String timeTaken;
//    private String requestURI;
//    private String requestMethod;
//    private String requestHeaderName;
//    private String contentType;
//    private String requestID;
//    private String hostName;
//    private String response;
//    private String errorTrace;
//}
