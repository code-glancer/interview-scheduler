package com.code.glancer.interview.scheduler.controller;

import com.code.glancer.interview.scheduler.response.TestResponse;
import com.code.glancer.interview.scheduler.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    @Test
    public void testGetAllCandidates() throws Exception {
        // Mock the behavior of CandidateService
        when(candidateService.getCandidate(Mockito.anyString())).thenReturn(TestResponse.getCandidateDto());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/candidates/{email}", "contact.rohan@codeglancer.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Rohan Kumar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Phesar"));
    }

}
