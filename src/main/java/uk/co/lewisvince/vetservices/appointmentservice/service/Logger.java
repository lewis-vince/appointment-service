package uk.co.lewisvince.vetservices.appointmentservice.service;

import uk.co.lewisvince.vetservices.appointmentservice.model.LogRequest;

public interface Logger {
    void log(LogRequest request, Object response);
}
