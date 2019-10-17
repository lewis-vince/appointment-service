package uk.co.lewisvince.vetservices.appointmentservice.component.glue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.co.lewisvince.vetservices.appointmentservice.model.LogRequest;
import uk.co.lewisvince.vetservices.appointmentservice.service.Logger;

@Slf4j
@Component
public class StubLoggingService implements Logger {
    @Override
    public void log(LogRequest request, Object response) {
        log.info("LOGGED");
    }
}
