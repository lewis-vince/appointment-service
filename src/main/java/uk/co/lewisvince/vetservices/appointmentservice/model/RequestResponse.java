package uk.co.lewisvince.vetservices.appointmentservice.model;

public class RequestResponse {

    private LogRequest request;
    private Object response;

    public RequestResponse(LogRequest request, Object response) {
        this.request = request;
        this.response = response;
    }

    public LogRequest getRequest() {
        return request;
    }

    public void setRequest(LogRequest request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
