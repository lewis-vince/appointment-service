package uk.co.lewisvince.vetservices.appointmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.repository.AppointmentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Iterable<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(String appointmentId) {
        Optional<Appointment> result = appointmentRepository.findById(UUID.fromString(appointmentId));
        return result.orElse(null);
    }

    public void deleteById(String appointmentId) {
        appointmentRepository.deleteById(UUID.fromString(appointmentId));
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

}
