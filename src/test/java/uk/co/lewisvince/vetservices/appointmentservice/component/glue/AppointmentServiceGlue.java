package uk.co.lewisvince.vetservices.appointmentservice.component.glue;


import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentServiceGlue {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Before
    public void before() {
        response = null;
    }

    @Given("The appointment service is running")
    public void theAppointmentServiceIsRunning() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/health", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @When("A {string} request is sent to the path {string} with a body from the file {string}")
    public void aRequestIsSentToThePath(String requestVerb, String path, String bodyFileLocation) throws URISyntaxException {
        // TODO get body from file
        String tempBody = "";
        RequestEntity requestEntity = RequestEntity.method(HttpMethod.resolve(requestVerb), new URI(path)).body(tempBody);
        response = restTemplate.exchange(path, HttpMethod.resolve(requestVerb), requestEntity, String.class);
    }

    @Then("The response body matches the contents of the file {string}")
    public void theResponseBodyMatchesTheContentsOfTheFile(String expectedResponseBody) throws JSONException {
        // TODO get expected body from file
        String tempExpectedBody = "";

        if (tempExpectedBody.equals("") || tempExpectedBody == null) {
            assertNull(response.getBody());
        } else {
            JSONAssert.assertEquals(tempExpectedBody, response.getBody(), true);
        }
    }

    @And("The response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode) {
        assertEquals(HttpStatus.resolve(expectedStatusCode), response.getStatusCode());
    }
}
