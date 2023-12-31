package com.example.MicroserviceOne.interceptor;
import com.example.MicroserviceOne.entity.ServiceOneEntity;
import com.example.MicroserviceOne.repo.ServiceRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AuditInterceptor implements HandlerInterceptor {
    private WebClient.Builder builder;
    @Autowired
    private ServiceRepo serviceRepo;
    Date requestTime = new Date(); // Capture the current date and time
    private long startTime;
    private static final Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);

    //    @Autowired
//    @Qualifier("asyncExecutor")
//    private ThreadPoolTaskExecutor asyncExecutor;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Pre-handle logic to capture information
        startTime = System.currentTimeMillis();
        Date requestTime = new Date(); // Capture the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Request Time: " + dateFormat.format(requestTime));
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handler method");
    }

//    @Async("asyncExecutor")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ServiceOneEntity serviceOneEntity=new ServiceOneEntity();

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        Date responseTime = new Date(); // Capture the current date and time for response
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Get client ID from the header
        String clientId = request.getHeader("client-id");
        // Check if client ID is missing
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("Client ID is required in the request header");
        }

        //for error trace
        String errorStackTrace = null;
        if (ex != null) {
            // Capture the exception stack trace in a variable
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            errorStackTrace = sw.toString();
            System.out.println(" error trace : " + errorStackTrace);
        }

        //for response
        ContentCachingResponseWrapper wrapper;
        if (response instanceof ContentCachingResponseWrapper) {
            wrapper = (ContentCachingResponseWrapper) response;
        } else {
            wrapper = new ContentCachingResponseWrapper(response);
        }
        String responseContent = getResponse(wrapper);

        //To get Query parameter
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Query parameter 'queryParameter' is required.");
        }
            serviceOneEntity.setQueryParameter(name);
            serviceOneEntity.setAuditTime(LocalDateTime.now());
            serviceRepo.save(serviceOneEntity);

            //for storing into database
        serviceOneEntity.setClientId(clientId);
        serviceOneEntity.setRequestTime(dateFormat.format(startTime));
        serviceOneEntity.setResponseTime(dateFormat.format(responseTime));
        serviceOneEntity.setStatusCode(response.getStatus());
        serviceOneEntity.setTimeTaken(String.valueOf(timeTaken));
        serviceOneEntity.setRequestURI(request.getRequestURI());
        serviceOneEntity.setRequestMethod(request.getMethod());
        serviceOneEntity.setRequestHeaderName(getRequestHeaderNames(request));
        serviceOneEntity.setContentType(request.getContentType());
        serviceOneEntity.setRequestID(generateRequestId());
        serviceOneEntity.setHostName(request.getServerName());
        serviceOneEntity.setResponse(responseContent);
        serviceOneEntity.setErrorTrace(errorStackTrace);
        serviceOneEntity.setQueryParameter(request.getQueryString());
        serviceOneEntity.setAuditTime(LocalDateTime.now());

      serviceRepo.save(serviceOneEntity);

     //Perform web client
        WebClient webClient = WebClient.create();
        webClient.post()
                .uri("http://localhost:8083/api/data")
                .body(BodyInserters.fromValue(serviceOneEntity))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    private String getRequestHeaderNames(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headerNamesStr = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerNamesStr.append(headerName).append(", ");
        }
        return headerNamesStr.toString();
    }


    @Async
    private String getResponse(ContentCachingResponseWrapper contentCachingResponseWrapper) {
        logger.info("Perform this method asyncronously");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try
            {
                String response = IOUtils.toString(contentCachingResponseWrapper.getContentAsByteArray(), contentCachingResponseWrapper.getCharacterEncoding());
                return response;
            }
            catch (Exception e) {
                logger.error("Error getting response asynchronously", e);
                return "Error occurred";

            }
        });
        logger.info("thread executed");
        return future.join();
    }

    //For Alpha-numeric Request Id
    public static String generateRequestId() {
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString().replaceAll("-", ""); // Remove hyphens
        String alphanumericCharacters = string.replaceAll("[^A-Za-z0-9]", ""); // Remove non-alphanumeric characters
        while (alphanumericCharacters.length() < 10) {
            alphanumericCharacters += generateRandomAlphanumeric();
        }
        return alphanumericCharacters.substring(0, 10);
    }

    private static String generateRandomAlphanumeric() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int randomIndex = (int) (Math.random() * characters.length());
        return characters.substring(randomIndex, randomIndex + 1);
    }
}


