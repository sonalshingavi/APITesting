/**
 * Will work on toDO part once's, EXP-15 is resolved.
 */

package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class PUTOrderStep {

    Response response;

    @Given("I Perform PUT operation for {string} with body as")
    public void iPerformPUTOperationForWithBodyAs(String arg0) {
    // toDo
    }

    @And("I should see the error message in PUT response as {string}")
    public void iShouldSeeTheErrorMessageInPUTResponseAs(String arg0) {
        // toDO
    }

    @Then("I receive a valid HTTP response code for PUT {int}")
    public void iReceiveAValidHTTPResponseCodeForPUT(int code) {
        Assert.assertEquals(response.getStatusCode(), code);
    }

    @And("I Perform PUT operation for saved Id {string} with body as")
    public void iPerformPUTOperationForSavedIdWithBodyAs(String arg0) {
        // toDo
    }
}
