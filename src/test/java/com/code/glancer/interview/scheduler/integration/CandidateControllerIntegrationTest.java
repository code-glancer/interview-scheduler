package com.code.glancer.interview.scheduler.integration;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.dto.CandidateDto;
import com.code.glancer.interview.scheduler.repository.CandidateRepository;
import com.code.glancer.interview.scheduler.response.TestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CandidateControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CandidateRepository candidateRepository;

    @Test()
    @Sql(scripts = "/sql/insert-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetCandidateByEmailId() {
        // Define a sample candidate and its EmailId
        String emailId = "john.doe@example.com";
        // Perform a GET request to the controller endpoint with the path parameter
        ResponseEntity<CandidateDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/candidates/" + emailId,
                CandidateDto.class);

        // Assert the response status code
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        CandidateDto candidate = responseEntity.getBody();
        assertThat(candidate).isNotNull();
        assertThat(candidate.getName()).isEqualTo("John Doe");
    }

    @Test()
    public void test_CreateCandidate() {
        // Perform a GET request to the controller endpoint with the path parameter
        ResponseEntity<CandidateDto> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/candidates", TestResponse.getCandidateDto(),
                CandidateDto.class);

        // Assert the response status code
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        CandidateDto candidate = responseEntity.getBody();
        assertThat(candidate).isNotNull();
        assertThat(candidate.getName()).isEqualTo("Rohan Kumar");

        Candidate savedCandidate = candidateRepository.findByEmail("contact.rohan@codeglancer.com");
        assertThat(savedCandidate).isNotNull();
        assertThat(savedCandidate.getName()).isEqualTo("Rohan Kumar");
    }
}
