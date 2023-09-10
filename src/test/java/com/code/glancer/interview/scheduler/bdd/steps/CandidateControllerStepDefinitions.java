package com.code.glancer.interview.scheduler.bdd.steps;


import com.code.glancer.interview.scheduler.dto.CandidateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

public class CandidateControllerStepDefinitions {

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
    ;
    private HttpHeaders headers;
    private ResponseEntity<String> responseEntity;
    private ObjectMapper objectMapper = new ObjectMapper();
    private String uniqueEmail;

    @Given("the candidate API is available")
    public void the_candidate_api_is_available() {
        // Write code here that turns the phrase above into concrete actions
        //  throw new io.cucumber.java.PendingException();
    }

    @Given("def uniqueEmail = {string} + java.util.UUID.randomUUID\\() + {string}")
    public void aDynamicallyGeneratedUniqueEmail(String string, String string2) {
        uniqueEmail = string + java.util.UUID.randomUUID() + string2;
    }

    @When("a POST request is made to {string} with the following JSON request body:")
    public void aPOSTRequestIsMadeToWithTheFollowingJSONRequestBody(String endpoint, String jsonRequestBody) {
        String url = "http://localhost:8081" + endpoint; // Update with your actual API URL
        jsonRequestBody = jsonRequestBody.replace("#(uniqueEmail)", uniqueEmail);

        // Set up headers
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create a CandidateDto from the JSON request body
        CandidateDto candidateDto = null;
        candidateDto = getCandidateDto(jsonRequestBody);

        // Create an HttpEntity with the CandidateDto and headers
        HttpEntity<CandidateDto> requestEntity = new HttpEntity<>(candidateDto, headers);

        // Make the POST request
        responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
    }


    @Then("the response status code should be {int}")
    public void theResponseShouldContainTheFollowingJSON(int statusCode) {


    }

    @Then("the response should contain the following JSON:")
    public void the_response_should_contain_the_following_json(String expectedJson) {
        String responseBody = responseEntity.getBody();

        // Deserialize the actual JSON response
        CandidateDto actualCandidateDto = getCandidateDto(responseBody);
        // Deserialize the expected JSON
        CandidateDto expectedCandidateDto = getCandidateDto(expectedJson);
        // Compare the actual and expected objects
        Assert.assertEquals(expectedCandidateDto.getName(), actualCandidateDto.getName());
    }

    private CandidateDto getCandidateDto(String jsonRequestBody) {
        CandidateDto candidateDto;
        try {
            candidateDto = objectMapper.readValue(jsonRequestBody, CandidateDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON request body", e);
        }
        return candidateDto;
    }

}
