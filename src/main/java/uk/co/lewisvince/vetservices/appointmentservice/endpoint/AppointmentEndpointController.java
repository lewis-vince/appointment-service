package uk.co.lewisvince.vetservices.appointmentservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.service.AppointmentService;

import java.util.List;

@RestController("/api/v1")
public class AppointmentEndpointController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("appointments")
    List<Appointment> getAll() {
        return appointmentService.getAll();
    }

    @GetMapping("appointment/{appointmentId}")
    Appointment getById(@PathVariable String appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @DeleteMapping("appointment/{appointmentId}")
    Appointment deleteById(@PathVariable String appointmentId) {
        return appointmentService.deleteById(appointmentId);
    }
}
