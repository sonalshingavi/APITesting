#Feature: DELETE Order
#  Verify DELETE operations using REST-assured for Order API
#
#  @smoke
#  Scenario: Verify DELETE Order by ID API with correct/valid OrderId
#    Given I perform DELETE operation for "/api/Order/12"
#    Then I receive a valid HTTP response code 200
#    And the response should indicate successful deletion
#
#  @negative
#  Scenario: Negative Test: Verify Response of DELETE Order API with incorrect/invalid OrderId
#    Given I perform DELETE operation for an invalid Order ID
#    Then I receive a HTTP response code 404
#    And the response should indicate the order does not exist or cannot be deleted
#
#  @negative
#  Scenario: Negative Test: Verify Response of DELETE Order API with non-existent OrderId
#    Given I perform DELETE operation for a non-existent Order ID
#    Then I receive a HTTP response code 404
#    And the response should indicate the order does not exist or cannot be deleted
