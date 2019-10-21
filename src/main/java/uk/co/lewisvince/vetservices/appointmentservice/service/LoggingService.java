package uk.co.lewisvince.vetservices.appointmentservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.lewisvince.vetservices.appointmentservice.model.LogRequest;
import uk.co.lewisvince.vetservices.appointmentservice.model.RequestResponse;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class LoggingService implements Logger{

    @Value("${logging-service.url}")
    private String loggingServiceUrl;

    private URI loggingServiceUri;

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void createLoggingServiceUri() throws URISyntaxException {
        loggingServiceUri = new URI(loggingServiceUrl);
    }

    public void log(LogRequest request, Object response) {
        RequestResponse requestResponse = new RequestResponse(request, response);
        log.info("Sending request/response to logging service...");
        restTemplate.postForEntity(loggingServiceUrl, RequestEntity.method(POST, loggingServiceUri)
                .contentType(APPLICATION_JSON).body(requestResponse), String.class);
        log.info("Request/response sent to logging service");
    }
}
