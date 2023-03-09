package com.code.glancer.interview.scheduler.service;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.dto.CandidateDto;

public interface CandidateService {

    CandidateDto createCandidate(CandidateDto candidateDto);

    CandidateDto getCandidate(String email);

    Candidate findCandidateByEmail(String email);
}
