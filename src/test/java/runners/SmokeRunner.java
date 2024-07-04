package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"stepdefinitions"}, // Package where step definitions are located
        tags = "@smoke and not @ignore", // Include smoke tests but exclude ignored tests
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json"} // Report plugins
)

public class SmokeRunner extends AbstractTestNGCucumberTests {

}


