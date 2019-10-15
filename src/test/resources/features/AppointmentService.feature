Feature: Get all appointments

  Scenario: Request for all appointments returns all appointments from store
    Given The appointment service is running
    When A "GET" request is sent to the path "/api/v1/appointments" with a body from the file "responses/emptyBody.json"
    Then The response body matches the contents of the file "responses/getAll_success.json"
    And The response status code is 200

  Scenario: Request for appointment by ID returns expected result
    Given The appointment service is running
    When A "GET" request is sent to the path "/api/v1/appointment/9a16ba1c-ef42-43a0-a3cf-06a9bdd5999b" with a body from the file ""
    Then The response body matches the contents of the file "responses/getById_success_1.json"
    And The response status code is 200

  Scenario: Request to create new appointment successfully creates appointment
    Given The appointment service is running
    When A "POST" request is sent to the path "/api/v1/appointment" with a body from the file "requests/createAppointment_1.json"
    Then The response body matches the contents of the file "responses/createAppointment_1_success.json", ignoring the root level ID field
    And The response status code is 200