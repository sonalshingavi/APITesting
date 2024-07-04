package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.Order;
import org.testng.Assert;
import utilities.RestAssuredExtension;

import java.util.List;

public class GETOrderStep<response> {

    Response response;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String uri) {

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(uri, "GET");
        response = (Response) restAssuredExtension.ExecuteAPI();
    }


    @Then("I receive a valid HTTP response code {int}")
    public void validateHTTPResponseCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("the response contains the order details for Order ID {int}")
    public void the_response_contains_the_order_details_for_order_id(int orderId) {

        System.out.println("response: " + response.getBody().prettyPrint());
        Assert.assertEquals(response.jsonPath().getInt("Id"), orderId);
        int id = response.jsonPath().getInt("Id");
        String name = response.jsonPath().getString("Name");
        Assert.assertNotNull(name);
    }

    @Given("I ensure the order list is empty")
    public void iEnsureTheOrderListIsEmpty() {

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension("/api/Order", "GET");
        response = (Response) restAssuredExtension.ExecuteAPI();
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseBody = response.getBody().asString();

        if (responseBody.isEmpty() || responseBody.equals("[]")) {
            System.out.println("Response is empty.");
        } else {
            System.out.println("Response is not empty. Deleting orders...");
            // toDO Add logic to delete the all orders
            // block due to EXP-16
        }
    }

    @And("the response contains the error message {string}")
    public void theResponseContainsTheErrorMessage(String arg0) {
        
    }

    @And("the response contains an empty list of orders")
    public void theResponseContainsAnEmptyListOfOrders() {

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension("/api/Order", "GET");
        response = (Response) restAssuredExtension.ExecuteAPI();
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.isEmpty() || responseBody.equals("[]"), "The response is not empty or not equal to []");
    }

    @Then("I {string} see the body with Name as {string}")
    public void iSeeTheBodyWithNameAs(String isShould, String name) {
    }

    @And("the response contains a list of orders")
    public void theResponseContainsAListOfOrders() {

        List<Order> orders = response.jsonPath().getList(".", Order.class);
        Assert.assertNotNull(orders, "The response does not contain a list of orders");
        Assert.assertTrue(orders.size() > 0, "The list of orders is empty");

        // response.jsonPath().getString("name")
    }

    @And("check one list response in detail")
    public void checkOneListResponseInDetail() {
        List<Order> orders = response.jsonPath().getList(".", Order.class);
        Assert.assertTrue(orders.size() > 0, "The list of orders is empty");

        // Checking the first order in detail
        Order firstOrder = orders.get(0);
        //Assert.assertNotNull(firstOrder.getId(), "Order ID is null");
       // Assert.assertNotNull(firstOrder.getName(), "Order Name is null");
    }
}
