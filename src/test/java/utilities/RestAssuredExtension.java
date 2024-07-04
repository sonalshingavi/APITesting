package utilities;


import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import models.Order;

import java.util.Map;


public class RestAssuredExtension {

    public static RequestSpecification Request;
    private RequestSpecBuilder builder;
    private String method;
    private String url;


    public RestAssuredExtension() {
        // Default constructor
    }


    public RestAssuredExtension(String uri, String method) {

        this.builder = new RequestSpecBuilder();
        this.url = "https://bar.bagconsult.eu" + uri;
        this.method = method;

    }


    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE/PUT
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        System.out.println("Request URL: " + this.url);

        switch (this.method.toUpperCase()) {
            case "POST":
                return request.post(this.url);
            case "DELETE":
                return request.delete(this.url);
            case "GET":
                return request.get(this.url);
            case "PUT":
                return request.put(this.url);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + this.method);
        }
    }

    /**
     * ExecuteWithBody
     * @param body
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteWithBody(Order body) {
//        builder.setBody(body);
//        builder.setContentType(ContentType.JSON);
//        return ExecuteAPI();

        try {
            // Serialize Order object to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(body);

            // Log the JSON payload
            System.out.println("JSON Payload: " + jsonBody);

            // Set the JSON body and content type
            builder.setBody(jsonBody);
            builder.setContentType(ContentType.JSON);

            return ExecuteAPI();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to serialize Order object to JSON", e);
        }


    }


    /**
     * ExecuteWithPathParams
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }


    /**
     * ExecuteWithPathParamsAndBody
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }
}
