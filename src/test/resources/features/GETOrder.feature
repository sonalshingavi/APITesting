Feature: GETOrder
  Verify different GET operations using REST-assured

  @smoke
  Scenario: Verify API Response for get all order API.
    Given  I perform GET operation for "/api/Order"
    Then I receive a valid HTTP response code 200
    And the response contains a list of orders
   # And check one list response in detail
    #And the response contains a list of orders


 @smoke
  Scenario: Verify GET Order by ID API with correct/valid OrderId
    Given  I perform GET operation for "/api/Order/12"
    Then I receive a valid HTTP response code 200
    And the response contains the order details for Order ID 12

  @ignore
  Scenario: Negative Test - Verify Response of GET Order API when orders are empty
    Given I ensure the order list is empty
    When I perform GET operation for "/api/Order"
    Then I receive a valid HTTP response code 200
    And the response contains an empty list of orders

  @ignore
  Scenario: Negative Test - Verify GET Order by ID API with incorrect/invalid OrderId
    Given I perform GET operation for "/api/Order/invalidId"
    Then I receive a valid HTTP response code 404
    And the response contains the error message "Order not found"
