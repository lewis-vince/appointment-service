package uk.co.lewisvince.vetservices.appointmentservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallGetAll_callsServiceAndReturnsResult() {
        List<Appointment> testList = new ArrayList<>();
        when(repository.findAll()).thenReturn(testList);

        Iterable<Appointment> result = service.getAll();

        assertEquals(testList, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetAppointmentById_withExistingResult_returnsResult() {
        UUID testUuid = UUID.randomUUID();
        Appointment testAppointment = new Appointment();
        when(repository.findById(testUuid)).thenReturn(Optional.of(testAppointment));

        Appointment result = service.getAppointmentById(testUuid.toString());

        assertEquals(testAppointment, result);
        verify(repository, times(1)).findById(testUuid);
    }

    @Test
    public void testGetAppointmentById_withNoMatchingResult_returnsNull() {
        UUID testUuid = UUID.randomUUID();
        when(repository.findById(testUuid)).thenReturn(Optional.empty());

        Appointment result = service.getAppointmentById(testUuid.toString());

        assertNull(result);
        verify(repository, times(1)).findById(testUuid);
    }

    @Test
    public void testDeleteAppointment_callsRepositoryWithPassedId() {
        UUID testUUID = UUID.randomUUID();

        service.deleteById(testUUID.toString());

        verify(repository, times(1)).deleteById(testUUID);
    }

    @Test
    public void testCreateAppointment_callsRepository(){
        Appointment testAppointment = new Appointment();
        Appointment testAppointment2 = new Appointment();
        when(repository.save(testAppointment)).thenReturn(testAppointment2);

        Appointment result = service.createAppointment(testAppointment);

        assertEquals(testAppointment2, result);
        verify(repository, times(1)).save(testAppointment);
    }
}
