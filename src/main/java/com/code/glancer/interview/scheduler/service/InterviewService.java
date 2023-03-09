package com.code.glancer.interview.scheduler.service;

import com.code.glancer.interview.scheduler.dto.InterviewDto;

import java.util.List;

public interface InterviewService {

    InterviewDto scheduleInterview(InterviewDto interviewDto);

    List<InterviewDto> getInterview(String schedulerEmail);
}
