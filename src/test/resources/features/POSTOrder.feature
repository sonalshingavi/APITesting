Feature: POSTOrder
  Verify different POST operations using REST-assured

 @smoke
 Scenario: Verify POST operation for creating an order with valid payload
   Given I perform POST operation for "/api/Order" with body
   """
      {
        "IsAdult": true,
        "TypeId": 1,
        "Quantity": 10,
        "Name": "Sonal",
        "Email": "sonal@gmail.com",
        "Price": 3.40
      }
   """
   Then I receive a valid HTTP response code for POST 201
   And the response contains the order details with Name "Sonal"
   And check the POST response and save order id value
   And I perform GET operation for saved Id and Validate the response


  Scenario: Verify when payload is valid, response should contain total price = Quantity * Price
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": true,
        "TypeId": 1,
        "Quantity": 3,
        "Name": "newuser",
        "Email": "newuser@gmail.com",
        "Price": 3.40
      }
      """
    Then I receive a valid HTTP response code for POST 201
    And the response contains the order details with Price 10.20

  @ignore
  Scenario: Negative - Verify "price" param in payload for size 0.330 should be 3.40 else throw error
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": true,
        "TypeId": 1,
        "Quantity": 1,
        "Name": "sample name",
        "Email": "sample@gmail.com",
        "Price": 30.40
      }
      """
    Then I receive a valid HTTP response code for POST 400
    And I should see the error message for POST as "The price for size 0.330 must be 3.40."

    @ignore
  Scenario: Negative - Verify "price" param in payload for size 0.500 should be 5.60 else throw error
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": true,
        "TypeId": 0,
        "Quantity": 1,
        "Name": "sample name",
        "Email": "sample@gmail.com",
        "Price": 5
      }
      """
    Then I receive a valid HTTP response code for POST 400
    And I should see the error message for POST as "The price for size 0.500 must be 5.60."

  @ignore
  Scenario: Verify some missing param in payload should throw error
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": true,
        "TypeId": 1,
        "Quantity": 1,
        "Email": "sample string 5",
        "Price": 3.40
      }
      """
    Then I receive a valid HTTP response code for POST 400
    And I should see the error message for POST as "The 'name' field is required."

  @ignore
  Scenario: Verify if IsAdult is false, should not create order
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": false,
        "TypeId": 0,
        "Quantity": 3,
        "Name": "sample string 4",
        "Email": "abc@gmail.com",
        "Price": 5.60
      }
      """
    Then I receive a valid HTTP response code for POST 400
    And I should see the error message for POST as "Purchaser's age should be greater than 18."

    @ignore
  Scenario: Verify if Quantity is <= 0, should not create order
    Given I perform POST operation for "/api/Order" with body
      """
      {
        "IsAdult": true,
        "TypeId": 1,
        "Quantity": 0,
        "Name": "sample string 4",
        "Email": "s@gmail.com",
        "Price": 3.40
      }
      """
    Then I receive a valid HTTP response code for POST 400
    And I should see the error message for POST as "The 'quantity' field must be a positive integer."