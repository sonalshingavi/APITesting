Feature: POSTOrder
  Verify different POST operations using REST-assured

 @smoke
  Scenario: Verify POST operation for creating an order
    Given I perform POST operation for "/api/Order" with body
      | IsAdult  | TypeId | Quantity | Name           | Email             | Price |
      | true     | 1      | 1        | sample string 4| sample string 5   | 3.40  |
   Then I I receive a valid HTTP response code 201
    And the response contains the order details with Name "Sonal"
