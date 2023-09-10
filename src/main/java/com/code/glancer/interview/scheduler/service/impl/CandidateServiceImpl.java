package com.code.glancer.interview.scheduler.service.impl;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.dto.CandidateDto;
import com.code.glancer.interview.scheduler.mapper.CandidateMapper;
import com.code.glancer.interview.scheduler.repository.CandidateRepository;
import com.code.glancer.interview.scheduler.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        Candidate candidate = candidateMapper.toCandidate(candidateDto);
        candidate = candidateRepository.save(candidate);
        candidateDto = candidateMapper.toCandidateDto(candidate);
        return candidateDto;
    }

    @Override
    public CandidateDto getCandidate(String email) {
        Candidate candidate = candidateRepository.findByEmail(email);
        CandidateDto candidateDto = candidateMapper.toCandidateDto(candidate);
        return candidateDto;
    }

    @Override
    public Candidate findCandidateByEmail(String email) {
        Candidate candidate = candidateRepository.findByEmail(email);
        return candidate;
    }
}
