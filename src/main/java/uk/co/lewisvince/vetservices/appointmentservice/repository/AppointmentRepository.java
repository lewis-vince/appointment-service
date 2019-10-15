package uk.co.lewisvince.vetservices.appointmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAppointmentById(@Param("id") UUID appointmentId);
}
