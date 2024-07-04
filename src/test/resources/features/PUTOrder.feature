@ignore
Feature: Verify PUT operation on Orders

  @smoke
  Scenario: Verify Successful Update of an Existing Order
    Given I perform POST operation for "/api/Order" with body
      | IsAdult  | Id | TypeId | Quantity | Name           | Email             | Price | Date                          |
      | true     | 2  | 3      | 1        | sample string 4| sample string 5   | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    And I Perform PUT operation for "/api/Order/15078" with body as
      | IsAdult  | Id | TypeId | Quantity | Name           | Email             | Price | Date                          |
      | true     | {Id}  | 3      | 1        | updated string | updated email     | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    And I receive a valid HTTP response code for PUT 200
    And I perform GET operation for "/api/Order/{Id}"
      | Id |
      | {Id} |
    Then I "should" see the body with Name as "updated string"


  Scenario: Negative - Verify Update API with some Missing params from Payload ("Name", "IsAdult")
    Given I Perform PUT operation for "/api/Order/15078" with body as
      | Id | TypeId | Quantity | Email             | Price | Date                          |
      | 2  | 3      | 1        | updated email     | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Missing required parameters: Name, IsAdult"


  Scenario: Negative - Verify Update with Name param with "null" or empty value
    Given I Perform PUT operation for "/api/Order/15078" with body as
      | IsAdult  | Id | TypeId | Quantity | Name | Email             | Price | Date                          |
      | true     | 2  | 3      | 1        | null | updated email     | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Name cannot be null or empty"


  Scenario: Negative - Verify Update with Invalid Email Format
    Given I Perform PUT operation for "/api/Order/15078" with body as
      | IsAdult  | Id | TypeId | Quantity | Name           | Email         | Price | Date                          |
      | true     | 2  | 3      | 1        | updated string | invalid-email | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Invalid email format"

  @smoke
  Scenario: Verify Update with IsAdult Set to False
    Given I Perform PUT operation for "/api/Order/15078" with body as
      | IsAdult  | Id | TypeId | Quantity | Name           | Email             | Price | Date                          |
      | false    | 2  | 3      | 1        | updated string | updated email     | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Purchaser's age should be greater than 18."

  @smoke
  Scenario: Negative - Verify Update with Price Mismatch for Size
    Given I Perform PUT operation for "/api/Order/15078" with body as
      | IsAdult  | Id | TypeId | Quantity | Name           | Email             | Price | Date                          |
      | true     | 2  | 3      | 1        | updated string | updated email     | 100.0 | 2024-06-18T12:55:45.8726273+03:00 |
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Price mismatch for given size"


  Scenario: Negative - Verify Update with Invalid Negative Quantity
    Given I Perform PUT operation for "/api/Order/15078" with body as
      | IsAdult  | Id | TypeId | Quantity | Name           | Email             | Price | Date                          |
      | true     | 2  | 3      | -1       | updated string | updated email     | 6.0   | 2024-06-18T12:55:45.8726273+03:00 |
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Quantity cannot be negative"


  Scenario: Negative - Verify Update with Invalid JSON
    Given I Perform PUT operation for "/api/Order/15078" with body as
      """
      {
        "IsAdult": true,
        "Id": 2,
        "TypeId": 3,
        "Quantity": 1,
        "Name": "updated string",
        "Email": "updated email",
        "Price": 6.0,
        "Date": "2024-06-18T12:55:45.8726273+03:00"
      """
    Then I receive a valid HTTP response code for PUT 400
    And I should see the error message in PUT response as "Invalid JSON payload"
