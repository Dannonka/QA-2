package cucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:scenario/weather.feature"},
        glue = {"stepDefs"})

public class weatherRequesterTest {

}
