package uk.co.lewisvince.vetservices.appointmentservice.component.glue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.lewisvince.vetservices.appointmentservice.service.Logger;

@Configuration
public class LoggingServiceConfig {

    @Autowired
    private StubLoggingService loggingService;

    @Bean
    public Logger loggingService() {
        return loggingService;
    }
}
