Feature: GETOrder
  Verify different GET operations using REST-assured

  @smoke
  Scenario: Verify API Response for get all order API.
    Given  I perform GET operation for "/api/Order"
    Then I receive a valid HTTP response code 200
    #And the response contains a list of orders


 

      @smoke
      Scenario: Verify GET Order by ID API with correct/valid OrderId
        Given  I perform GET operation for "/api/Order/12"
        Then I receive a valid HTTP response code 200
        And the response contains the order details for Order ID 12


    # neg toDo
    #Scenario: Negative Test: Verify Response of get order API when orders are empty


    # neg toDo
    #Scenario: Verify GET Order by ID API with incorrect/ invalid OrderId

