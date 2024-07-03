package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"stepdefinitions"}, // Package where step definitions are located
        tags = "@smoke", // Specify the tag for smoke tests
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"} // Report plugins
)
public class TestRunner extends AbstractTestNGCucumberTests {

}


//    // POST Feature
//    @CucumberOptions(
//            features =  "src/test/resources/features/POSTOrder.feature", // Path to POST feature files
//            glue = {"stepdefinitions"}, // Package where step definitions are located
//            tags = "@smoke", // Specify the tag for smoke tests
//            plugin = {"pretty", "html:target/cucumber-reports/post"} // Report plugins for POST
//    )
//    public static class PostTestRunner {
//    }
//
//    // GET Feature
//    @CucumberOptions(
//            features = "src/test/resources/features/GETOrder.feature", // Path to GET feature files
//            glue = {"stepdefinitions"}, // Package where step definitions are located
//            tags = "@smoke", // Specify the tag for smoke tests
//            plugin = {"pretty", "html:target/cucumber-reports/get"} // Report plugins for GET
//    )
//    public static class GetTestRunner {
//    }
//
//    // DELETE Feature (if you have a separate feature for DELETE)
//    @CucumberOptions(
//            features = "src/test/resources/features/DeleteOrder", // Path to DELETE feature files
//            glue = {"stepdefinitions"}, // Package where step definitions are located
//            tags = "@smoke", // Specify the tag for smoke tests
//            plugin = {"pretty", "html:target/cucumber-reports/delete"} // Report plugins for DELETE
//    )
//    public static class DeleteTestRunner {
//    }

