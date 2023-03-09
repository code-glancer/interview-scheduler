package com.code.glancer.interview.scheduler.service;

import com.code.glancer.interview.scheduler.domain.Talent;
import com.code.glancer.interview.scheduler.dto.TalentDto;

import java.util.List;

public interface TalentService {

    TalentDto createTalent(TalentDto talentDto);

    Talent getTalent(Long talentId);

    List<TalentDto> findAll();
}
