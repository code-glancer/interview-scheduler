Feature: Candidate Controller API
  Background:
    * def uniqueEmail = 'user' + java.util.UUID.randomUUID() + '@gmail.com'

  Scenario: Create a Candidate
    Given the candidate API is available
    When a POST request is made to "/candidates" with the following JSON request body:
      """
      {
        "name": "John Doe",
        "email": "#(uniqueEmail)",
        "address": "phesar",
        "mobile": "6300366542",
        "ctc": 2500000,
        "ectc": 2500000,
        "location": "Hyderabad",
        "notice": "Serving Notice of 30 days"
      }
      """
    Then the response status code should be 201
    And the response should contain the following JSON:
      """
      {
        "name": "John Doe",
        "email": "#(uniqueEmail)",
        "address": "phesar",
        "mobile": "6300366542",
        "ctc": 2500000,
        "ectc": 2500000,
        "location": "Hyderabad",
        "notice": "Serving Notice of 30 days"
      }
      """

  Scenario: Get Candidate by Email (Not Found)
    Given the candidate API is available
    And no candidate with email "nonexistent@example.com" exists
    When a GET request is made to "/candidates/nonexistent@example.com"
    Then the response status code should be 404

