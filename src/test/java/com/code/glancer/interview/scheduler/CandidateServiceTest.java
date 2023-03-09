package com.code.glancer.interview.scheduler;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.dto.CandidateDto;
import com.code.glancer.interview.scheduler.mapper.CandidateMapper;
import com.code.glancer.interview.scheduler.mapper.CandidateMapperImpl;
import com.code.glancer.interview.scheduler.repository.CandidateRepository;
import com.code.glancer.interview.scheduler.service.impl.CandidateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CandidateServiceImpl.class, CandidateMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class CandidateServiceTest {

    @MockBean
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CandidateServiceImpl underTestService;

    @Test
    public void test_createCandidate() {
        Candidate expectedCandidate = getCandidate();
        Mockito.when(candidateRepository.save(Mockito.any(Candidate.class))).thenReturn(expectedCandidate);
        CandidateDto candidateInput = getCandidateDto();
        CandidateDto resultantCandidate = underTestService.createCandidate(candidateInput);
        Assertions.assertNotNull(resultantCandidate);

    }

    public Candidate getCandidate() {
        return Candidate.builder()
                .id(Long.valueOf(111))
                .name("Rohan Kumar")
                .address("Phesar")
                .email("contact.rohan@codeglancer.com")
                .mobile("9999999999")
                .ctc(30000000)
                .ectc(4000000)
                .location("Prayag Raj")
                .notice("60 days negotiable")
                .build();
    }

    public CandidateDto getCandidateDto() {
        return CandidateDto.builder()
                .name("Rohan Kumar")
                .address("Phesar")
                .email("contact.rohan@codeglancer.com")
                .mobile("9999999999")
                .ctc(30000000)
                .ectc(4000000)
                .location("Prayag Raj")
                .notice("60 days negotiable")
                .build();
    }
}
