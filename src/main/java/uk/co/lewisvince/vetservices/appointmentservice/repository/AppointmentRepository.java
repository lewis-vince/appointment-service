package uk.co.lewisvince.vetservices.appointmentservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "appointments", path = "appointments")
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, String> {

    List<Appointment> findAppointmentById(@Param("id") String appointmentId);
}
