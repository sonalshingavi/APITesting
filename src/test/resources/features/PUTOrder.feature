@ignore
Feature: PUTOrder
  Verify PUT operation on Orders

  @smoke
  Scenario: Verify Successful Update of an Existing Order
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 0,
        "Quantity": 1,
        "Name": "sample string 4",
        "Email": "abc@gmail.com",
        "Price": 5.60,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    And check the POST response and save order id value
    And I Perform PUT operation for saved Id "/api/Order/{Id}" with body as
      """
      {
        "IsAdult": true,
        "Id": {Id},
        "TypeId": 3,
        "Quantity": 1,
        "Name": "updated string",
        "Email": "updatedemail@gmail.com",
        "Price": 5.60,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    And I receive a valid HTTP response code for PUT 200
    And I perform GET operation for "/api/Order/{Id}"
    Then I "should" see the body with Name as "updated string"

  Scenario: Negative - Verify Update API with some Missing params from Payload ("Name", "IsAdult")
    Given I Perform PUT operation for "/api/Order/{Id}" with body as
      """
      {
        "Id": 2,
        "TypeId": 3,
        "Quantity": 1,
        "Email": "updatedemail@gmail.com",
        "Price": 6.0,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Missing required parameters: Name, IsAdult"

  Scenario: Negative - Verify Update with Name param with "null" or empty value
    Given I Perform PUT operation for "/api/Order/{Id}" with body as
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 3,
        "Quantity": 1,
        "Name": null,
        "Email": "updatedemail@gmail.com",
        "Price": 5.60,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Name cannot be null or empty"

  Scenario: Negative - Verify Update with Invalid Email Format
    Given I Perform PUT operation for "/api/Order/{Id}" with body as
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 3,
        "Quantity": 1,
        "Name": "updated string",
        "Email": "invalid-email",
        "Price": 3.40,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Invalid email format"

  @smoke
  Scenario: Verify Update with IsAdult Set to False
    Given I Perform PUT operation for "/api/Order/{Id}" with body as
      """
      {
        "IsAdult": false,
        "Id": 2,
        "TypeId": 3,
        "Quantity": 1,
        "Name": "updated string",
        "Email": "updatedemail@gmail.com",
        "Price": 5.60,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Purchaser's age should be greater than 18."

  @smoke
  Scenario: Negative - Verify Update with Price Mismatch for Size
    Given I Perform PUT operation for "/api/Order/{Id}" with body as
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 3,
        "Quantity": 1,
        "Name": "updated string",
        "Email": "updatedemail@gmail.com",
        "Price": 3.40,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Price mismatch for given size"

  Scenario: Negative - Verify Update with Invalid Negative Quantity
    Given I Perform PUT operation for "/api/Order/{ID}" with body as
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 3,
        "Quantity": -1,
        "Name": "updated string",
        "Email": "updatedemail@gmail.com",
        "Price": 5.60,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      }
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Quantity cannot be negative"

  Scenario: Negative - Verify Update with Invalid JSON
    Given I Perform PUT operation for "/api/Order/{Id}" with body as
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 3
        "Quantity": 1
        "Name": "updated string",
        "Email": "ss@gmail.com",
        "Price": 5.60,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Invalid JSON payload"
