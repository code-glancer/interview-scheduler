package com.code.glancer.interview.scheduler.controller;

import com.code.glancer.interview.scheduler.dto.InterviewDto;
import com.code.glancer.interview.scheduler.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping
    public HttpEntity<InterviewDto> scheduleInterview(@RequestBody InterviewDto interviewDto) {
        interviewDto = interviewService.scheduleInterview(interviewDto);
        return new ResponseEntity<>(interviewDto, HttpStatus.CREATED);
    }

    @GetMapping
    public HttpEntity<List<InterviewDto>> getInterview(@PathVariable String schedulerEmail) {
        List<InterviewDto> interviews = interviewService.getInterview(schedulerEmail);
        return new ResponseEntity<>(interviews, HttpStatus.OK);
    }
}
