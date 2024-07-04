package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/GETOrder.feature", // Path to your feature files
        glue = {"stepdefinitions"}, // Package where step definitions are located
        tags = "not @ignore", // Exclude scenarios tagged with @ignore
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json"} // Report plugins
)

public class GETRunner extends AbstractTestNGCucumberTests {
}
