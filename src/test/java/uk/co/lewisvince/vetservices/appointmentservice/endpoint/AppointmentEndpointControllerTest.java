package uk.co.lewisvince.vetservices.appointmentservice.endpoint;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.service.AppointmentService;
import uk.co.lewisvince.vetservices.appointmentservice.service.LoggingService;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppointmentEndpointControllerTest {

    @Mock
    private AppointmentService service;

    @Mock
    private LoggingService loggingService;

    @InjectMocks
    private AppointmentEndpointController controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAppointments_callsService() {
        Iterable<Appointment> testAppointments = new ArrayList<>();
        when(service.getAll()).thenReturn(testAppointments);

        Iterable<Appointment> result = controller.getAll();

        assertEquals(testAppointments, result);
        verify(service, times(1)).getAll();
        verify(loggingService, times(1)).log(any(), any());
    }

    @Test
    public void testGetAppointmentById_callsServiceWithCorrectId() {
        Appointment testAppointment = new Appointment();
        when(service.getAppointmentById("TEST")).thenReturn(testAppointment);

        Appointment result = controller.getById("TEST");

        assertEquals(testAppointment, result);
        verify(service, times(1)).getAppointmentById("TEST");
        verify(loggingService, times(1)).log(any(), any());
    }

    @Test
    public void testDeleteAppointment_callsServiceWithCorrectId() {
        controller.deleteById("TEST-ID");

        verify(service, times(1)).deleteById("TEST-ID");
        verify(loggingService, times(1)).log(any(), any());
    }

    @Test
    public void testCreateAppointment_callsServiceWithPassedAppointment() {
        Appointment testAppointment = new Appointment();
        Appointment testAppointment2 = new Appointment();
        when(service.createAppointment(testAppointment)).thenReturn(testAppointment2);

        Appointment result = controller.post(testAppointment);

        assertEquals(result, testAppointment2);
        verify(service, times(1)).createAppointment(testAppointment);
        verify(loggingService, times(1)).log(any(), any());
    }
}
