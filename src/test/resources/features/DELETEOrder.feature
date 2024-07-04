@ignore
Feature: DELETEOrder
  Verify different DELETE operations using REST-assured

  @smoke
  Scenario: Verify Successful Deletion of an Existing Order
    Given I perform POST operation for "/api/Order" with body
      | IsAdult  | TypeId | Quantity | Name           | Email             | Price |
      | true     | 1      | 1        | sample string 4| sample string 5   | 3.40  |
    And I Perform DELETE operation for "/api/Order/{Id}"
    Then I receive a valid HTTP response code for delete 200
    And I perform GET operation for "/api/Order/{Id}"
    Then I receive a valid HTTP response code 404
    And I "should not" see the body with Id as "101"

  Scenario: Negative - Verify Deletion of a Non-Existent Order
    Given I Perform DELETE operation for "/api/Order/{Id}"
    Then I receive a valid HTTP response code for delete 404
    And I should see the error message as "Order not found"

  Scenario: Negative - Verify Deletion of an Already Deleted Order
    Given I perform POST operation for "/api/Order" with body
      | IsAdult  | TypeId | Quantity | Name           | Email             | Price |
      | true     | 1      | 1        | user2          | user2@gmail.com   | 3.40  |
    And I Perform DELETE operation for "/api/Order/{Id}"
    And I Perform DELETE operation for "/api/Order/{Id}"
    Then I receive a valid HTTP response code for delete 404
    And I should see the error message as "Order not found"

  Scenario: Negative - Verify Deletion with Invalid Order ID
    Given I Perform DELETE operation for "/api/Order/{Id}"
      | ID |
      | invalid |
    Then I receive a valid HTTP response code for delete 404
    And I should see the error message as "Invalid Order ID"
