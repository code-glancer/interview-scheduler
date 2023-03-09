package com.code.glancer.interview.scheduler.mapper;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.dto.CandidateDto;
import org.mapstruct.Mapper;

@Mapper
public interface CandidateMapper {

    CandidateDto toCandidateDto(Candidate candidate);

    Candidate toCandidate(CandidateDto candidateDto);

}
