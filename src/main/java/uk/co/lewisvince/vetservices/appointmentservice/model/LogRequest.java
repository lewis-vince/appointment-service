package uk.co.lewisvince.vetservices.appointmentservice.model;

import org.springframework.http.HttpMethod;

public class LogRequest {

    private HttpMethod verb;
    private String serviceName;
    private String path;
    private Object body;

    public LogRequest(HttpMethod verb, String serviceName, String path, Object body) {
        this.verb = verb;
        this.serviceName = serviceName;
        this.path = path;
        this.body = body;
    }

    public HttpMethod getVerb() {
        return verb;
    }

    public void setVerb(HttpMethod verb) {
        this.verb = verb;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
