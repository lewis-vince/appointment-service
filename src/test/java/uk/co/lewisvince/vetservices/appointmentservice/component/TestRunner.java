package uk.co.lewisvince.vetservices.appointmentservice.component;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class TestRunner {
}
