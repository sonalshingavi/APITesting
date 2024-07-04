package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.Order;
import org.testng.Assert;
import utilities.RestAssuredExtension;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utilities.ValidateUtility;
import java.util.List;
public class GETOrderStep {

    Response response;
    final Gson gson = new Gson();

    @Given("I perform GET operation for \"([^\"]*)\"$")
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
        // toDo
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
        // toDo
    }

    @And("the response contains a list of orders")
    public void theResponseContainsAListOfOrders() {

        List<Order> orders = gson.fromJson(response.asString(), new TypeToken<List<Order>>(){}.getType());
        Assert.assertNotNull(orders, "The response does not contain a list of orders");
        Assert.assertFalse(orders.isEmpty(), "The list of orders is empty");
        System.out.println("The list contains " + orders.size() + " orders. ");
    }

    @And("check list response in detail")
    public void checkListResponseInDetail() {
        String responseBody = response.asString();

        // Check if the response is a list of orders or a single order
        if (responseBody.startsWith("[")) {
            // Convert response to list of orders
            List<Order> orders = gson.fromJson(responseBody, new TypeToken<List<Order>>(){}.getType());

            if (!orders.isEmpty()) {
                // Checking the first order in detail
                Order order = orders.get(0);
                ValidateUtility.validateOrder(order);
            } else {
                System.out.println("No orders present to validate");
            }
        } else {
            // Convert response to a single order
            Order order = gson.fromJson(responseBody, Order.class);
            ValidateUtility.validateOrder(order);
        }
    }
}
