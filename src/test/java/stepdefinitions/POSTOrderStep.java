package stepdefinitions;

import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.Order;
import org.testng.Assert;
import utilities.RestAssuredExtension;
import utilities.ValidateUtility;

import java.math.BigDecimal;

public class POSTOrderStep {

    private Response response;
    private int newlyCreatedId;
    final Gson gson = new Gson();
    private Order order;


    @Given("I perform POST operation for {string} with body")
    public void i_perform_post_operation_for_with_body(String url, String payload) {

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(url , "POST");
        order = new Gson().fromJson(payload, Order.class);
        response = (Response) restAssuredExtension.ExecuteWithBody(order);
    }

    @Then("I receive a valid HTTP response code for POST {int}")
    public void i_receive_a_valid_http_response_code_for_post(int statusCode) {

        System.out.println("response: " + response.getBody().prettyPrint());
        System.out.println("code: " + response.getStatusCode());
       Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("the response contains the order details with Name {string}")
    public void the_response_contains_the_order_details_with_name(String expectedName) {

        // Validate the name in the response
        Assert.assertEquals(response.jsonPath().getString("Name"), expectedName);
    }

    @And("check the POST response and save order id value")
    public void checkThePOSTResponseAndSaveOrderIdValue() {

        newlyCreatedId = response.jsonPath().getInt("Id");
        System.out.println("Id: " + newlyCreatedId);
        Assert.assertNotNull(newlyCreatedId, "Created order ID is null");
    }

    @And("I perform GET operation for saved Id and Validate the response")
    public void iPerformGETOperationForSavedIdAndValidateTheResponse() {

        RestAssuredExtension restAssuredExtension1 = new RestAssuredExtension("/api/Order/" + newlyCreatedId, "GET");
        Response getRes = (Response) restAssuredExtension1.ExecuteAPI();
        Assert.assertEquals(getRes.getStatusCode(), 200);
        Order order = gson.fromJson(getRes.asString(), Order.class);
        ValidateUtility.validateOrder(order);
    }

    @And("I should see the error message for POST as {string}")
    public void iShouldSeeTheErrorMessageForPOSTAs(String arg0) {
        // toDO
    }

    @And("the response contains the order details with Price (\\d+\\.\\d+)$")
    public void theResponseContainsTheOrderDetailsWithPrice(BigDecimal expectedPrice) {

        order = response.getBody().as(Order.class);
        Assert.assertEquals(order.getPrice(), expectedPrice,
                String.format("Expected price: %s, but got: %s", expectedPrice, order.getPrice()));
    }
}
