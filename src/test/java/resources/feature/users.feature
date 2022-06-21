Feature: Testing CURD operation using different request on the application.

  Scenario: User can send get request.
    When User sends a GET request to list endpoint
    Then User get back a valid status code 200

  Scenario: User can create data using post request.
    When User sends a Post request to list endpoint
    Then User get back a valid status code 201
    And Verify that user created sucessfully

  Scenario: User can Update data using patch request.
    When User sends a patch request to list endpoint
    Then User get back a valid status code 200
    And Verify that user Updated sucessfully

  Scenario: User can Delate data using delete request.
    When User sends a delete request to list endpoint
    Then User get back a valid status code 204












