/**
 * Will work on toDO part once's, EXP-16 is resolved.
 */

package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class DELETEOrderStep {

    Response response;

    @And("I Perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String arg0) {
        // toDo
    }

    @And("I should see the error message as {string}")
    public void iShouldSeeTheErrorMessageAs(String arg0) {
        // toDo
    }

    @And("I {string} see the body with Id as {string}")
    public void iSeeTheBodyWithIdAs(String arg0, String arg1) {
        // toDo
    }

    @Then("I receive a valid HTTP response code for delete {int}")
    public void iReceiveAValidHTTPResponseCodeForDelete(int code) {
        Assert.assertEquals(response.getStatusCode(), code);
    }
}
