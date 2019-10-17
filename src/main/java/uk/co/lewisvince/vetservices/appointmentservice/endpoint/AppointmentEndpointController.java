package uk.co.lewisvince.vetservices.appointmentservice.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.model.LogRequest;
import uk.co.lewisvince.vetservices.appointmentservice.service.AppointmentService;
import uk.co.lewisvince.vetservices.appointmentservice.service.LoggingService;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AppointmentEndpointController {

    @Autowired
    LoggingService loggingService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    Iterable<Appointment> getAll() {
        log.info("\"GET\"" + " request received at \"/api/v1/appointments\"");
        LogRequest logRequest = new LogRequest(HttpMethod.GET, "appointment-service", "/api/v1/appointments", null);
        Iterable<Appointment> response = appointmentService.getAll();
        loggingService.log(logRequest, response);
        return response;
    }

    @GetMapping("/appointment/{appointmentId}")
    Appointment getById(@PathVariable String appointmentId) {
        log.info("\"GET\"" + " request received at \"/api/v1/appointment/" + appointmentId + "\"");
        LogRequest logRequest = new LogRequest(HttpMethod.GET, "appointment-service", "/api/v1/appointment/" + appointmentId, null);
        Appointment response = appointmentService.getAppointmentById(appointmentId);
        loggingService.log(logRequest, response);
        return response;
    }

    @DeleteMapping("/appointment/{appointmentId}")
    void deleteById(@PathVariable String appointmentId) {
        log.info("\"DELETE\"" + " request received at \"/api/v1/appointment/" + appointmentId + "\"");
        LogRequest logRequest = new LogRequest(HttpMethod.DELETE, "appointment-service", "/api/v1/appointment/" + appointmentId, null);
        appointmentService.deleteById(appointmentId);
        loggingService.log(logRequest, "");
    }

    @PostMapping("/appointment")
    Appointment post(@RequestBody Appointment appointment) {
        log.info("\"POST\"" + " request received at \"/api/v1/appointment\"");
        LogRequest logRequest = new LogRequest(HttpMethod.POST, "appointment-service", "/api/v1/appointment", appointment);
        Appointment response = appointmentService.createAppointment(appointment);
        loggingService.log(logRequest, response);
        return response;
    }
}
