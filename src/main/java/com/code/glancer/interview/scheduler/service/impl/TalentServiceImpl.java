package com.code.glancer.interview.scheduler.service.impl;

import com.code.glancer.interview.scheduler.domain.Talent;
import com.code.glancer.interview.scheduler.dto.TalentDto;
import com.code.glancer.interview.scheduler.exception.ResourceNotFoundException;
import com.code.glancer.interview.scheduler.mapper.TalentMapper;
import com.code.glancer.interview.scheduler.repository.TalentRepository;
import com.code.glancer.interview.scheduler.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentServiceImpl implements TalentService {

    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private TalentMapper talentMapper;

    @Override
    public TalentDto createTalent(TalentDto talentDto) {
        Talent talent = talentMapper.toTalent(talentDto);
        talent = talentRepository.save(talent);
        talentDto = talentMapper.toTalentDto(talent);
        return talentDto;
    }

    @Override
    public Talent getTalent(Long talentId) {
        Talent talent = talentRepository.findById(talentId).orElseThrow(() -> new ResourceNotFoundException("Talent not found", HttpStatus.NOT_FOUND));
        return talent;
    }

    @Override
    public List<TalentDto> findAll() {
        List<Talent> talents = talentRepository.findAll();

        return null;
    }
}
