package uk.co.lewisvince.vetservices.appointmentservice.component.glue;


import com.google.common.io.Resources;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import uk.co.lewisvince.vetservices.appointmentservice.model.Appointment;
import uk.co.lewisvince.vetservices.appointmentservice.repository.AppointmentRepository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentServiceGlue {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Autowired
    private AppointmentRepository appointmentRepository;

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
    }

    @Before
    public void before() {
        response = null;
    }

    @Given("The appointment service is running")
    public void theAppointmentServiceIsRunning() throws JSONException {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONObject responseJson = new JSONObject(response.getBody());
        assertEquals(Status.UP.getCode(), responseJson.get("status"));
    }


    @When("A {string} request is sent to the path {string} with a body from the file {string}")
    public void aRequestIsSentToThePath(String requestVerb, String path, String bodyFileLocation) throws URISyntaxException, IOException {
        String requestBody = Resources.toString(Resources.getResource(bodyFileLocation), Charset.defaultCharset());
        RequestEntity requestEntity = RequestEntity.method(HttpMethod.resolve(requestVerb), new URI(path))
                .contentType(MediaType.APPLICATION_JSON).body(requestBody);
        response = restTemplate.exchange(path, HttpMethod.resolve(requestVerb), requestEntity, String.class);
    }

    @Then("The response body matches the contents of the file {string}")
    public void theResponseBodyMatchesTheContentsOfTheFile(String expectedResponseBody) throws JSONException, IOException {
        String expectedBody = Resources.toString(Resources.getResource(expectedResponseBody), Charset.defaultCharset());

        if (expectedBody.equals("")) {
            assertNull(response.getBody());
        } else {
            JSONAssert.assertEquals(expectedBody, response.getBody(), true);
        }
    }

    @Then("The response body matches the contents of the file {string}, ignoring the root level ID field")
    public void theResponseBodyMatchesTheContentsOfTheFile_ignoreRootId(String expectedResponseBody) throws JSONException, IOException {
        String expectedBody = Resources.toString(Resources.getResource(expectedResponseBody), Charset.defaultCharset());

        if (expectedBody.equals("")) {
            assertNull(response.getBody());
        } else {
            JSONAssert.assertEquals(expectedBody, response.getBody(),
                    new CustomComparator(JSONCompareMode.STRICT, new Customization("id", (o1, o2) -> true)));
        }
    }

    @And("The response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode) {
        assertEquals(HttpStatus.resolve(expectedStatusCode), response.getStatusCode());
    }

    @And("The appointment with id {string} is not present in the store")
    public void theAppointmentWithIdIsNotPresentInTheStore(String appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(UUID.fromString(appointmentId));
        assertFalse(appointment.isPresent());
    }
}
