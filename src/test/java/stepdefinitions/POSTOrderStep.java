package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Order;
import org.testng.Assert;
import utilities.RestAssuredExtension;
import java.math.BigDecimal;

public class POSTOrderStep {

    private Response response;
    private RequestSpecification request;

    @Given("I perform POST operation for {string} with body")
    public void i_perform_post_operation_for_with_body(String url, DataTable table) throws Throwable {
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(url , "POST");

        Order orderPayload = new Order();
        orderPayload.setName("Sonal");
        orderPayload.setEmail("sonal@gmail.com");
        orderPayload.setIsAdult(true);
        orderPayload.setQuantity(10);
        orderPayload.setTypeId(1);
        orderPayload.setPrice(BigDecimal.valueOf(3.40));

        response = (Response) restAssuredExtension.ExecuteWithBody(orderPayload);

    }

    @Then("I receive a valid HTTP response code for POST {int}")
    public void i_receive_a_valid_http_response_code_for_post(int statusCode) {
        System.out.println("response: " + response.getBody().prettyPrint());
        System.out.println("code: " + response.getStatusCode());
       Assert.assertEquals(response.getStatusCode(), statusCode);

    }

    @Then("the response contains the order details with Name {string}")
    public void the_response_contains_the_order_details_with_name(String expectedName) {

        System.out.println("########  check " + response.jsonPath().getString("name"));
        // Validate the name in the response
        Assert.assertEquals(response.jsonPath().getString("Name"), expectedName);

    }
}
