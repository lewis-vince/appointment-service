package uk.co.lewisvince.vetservices.appointmentservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.service.AppointmentService;

@RestController
@RequestMapping("/api/v1")
public class AppointmentEndpointController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    Iterable<Appointment> getAll() {
        return appointmentService.getAll();
    }

    @GetMapping("/appointment/{appointmentId}")
    Appointment getById(@PathVariable String appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @DeleteMapping("/appointment/{appointmentId}")
    void deleteById(@PathVariable String appointmentId) {
        appointmentService.deleteById(appointmentId);
    }

    @PostMapping("/appointment")
    Appointment post(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }
}
