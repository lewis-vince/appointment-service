Feature: Get all appointments
  Scenario: Request for all appointments returns all appointments from store
    Given The appointment service is running
    When A "GET" request is sent to the path "/api/v1/appointments" with a body from the file "responses/emptyBody.json"
    Then The response body matches the contents of the file "responses/getAll_success.json"
    And The response status code is 200

  Scenario: Request for appointment by ID returns expected result
    Given The appointment service is running
    When A "GET" request is sent to the path "/api/v1/appointment/123123" with a body from the file ""
    Then The response body matches the contents of the file "responses/getById_123123_success.json"
    And The response status code is 200