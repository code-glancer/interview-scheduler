package com.code.glancer.interview.scheduler.controller;

import com.code.glancer.interview.scheduler.dto.ResponseDto;
import com.code.glancer.interview.scheduler.dto.TalentDto;
import com.code.glancer.interview.scheduler.service.TalentService;
import com.code.glancer.interview.scheduler.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/talents")
@RestController
public class TalentController {

    @Autowired
    private TalentService talentService;

    @PostMapping
    public HttpEntity<ResponseDto> createTalent(@RequestBody TalentDto talentDto) {
        talentDto = talentService.createTalent(talentDto);
        return new ResponseEntity<>(ResponseUtil.getSuccessResponse(talentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public HttpEntity<ResponseDto> getAll() {
        List<TalentDto> talentDtos = talentService.findAll();
        return new ResponseEntity<>(ResponseUtil.getSuccessResponse(talentDtos), HttpStatus.OK);
    }
}
