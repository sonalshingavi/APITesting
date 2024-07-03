package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import utilities.RestAssuredExtension;

public class GETOrderStep<response> {


    Response response;


    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String uri) {
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(uri, "GET");
        response = (Response) restAssuredExtension.ExecuteAPI();
    }


    @Then("I receive a valid HTTP response code {int}")
    public void validateHTTPResponseCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("the response contains the order details for Order ID {int}")
    public void the_response_contains_the_order_details_for_order_id(int orderId) {
        System.out.println("response: " + response.getBody().prettyPrint());

        Assert.assertEquals(response.jsonPath().getInt("Id"), orderId);

        int id = response.jsonPath().getInt("Id");
//        System.out.println("Id: " + id);
//        System.out.println("Name: " + response.jsonPath().getString("Name"));


        String name = response.jsonPath().getString("Name");
        Assert.assertNotNull(name);

    }



    }
