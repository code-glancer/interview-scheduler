package com.code.glancer.interview.scheduler.mapper;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.domain.Interview;
import com.code.glancer.interview.scheduler.domain.Talent;
import com.code.glancer.interview.scheduler.domain.User;
import com.code.glancer.interview.scheduler.dto.InterviewDto;
import com.code.glancer.interview.scheduler.service.CandidateService;
import com.code.glancer.interview.scheduler.service.TalentService;
import com.code.glancer.interview.scheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterviewMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private TalentService talentService;

    public InterviewDto toInterviewDto(Interview interview) {
        return InterviewDto
                .builder()
                .id(interview.getId())
                .candidateEmailId(Optional.ofNullable(interview.getCandidate()).map(Candidate::getEmail).orElse(null))
                .interviewerEmailId(Optional.ofNullable(interview.getInterviewer()).map(User::getEmailId).orElse(null))
                .talentId(Optional.ofNullable(interview.getTalent()).map(Talent::getId).orElse(null))
                .interviewStatus(interview.getInterviewStatus())
                .schedulerEmailId(Optional.ofNullable(interview.getScheduler()).map(User::getEmailId).orElse(null))
                .meetingLink(interview.getMeetingLink())
                .dateTime(interview.getDateTime())
                .interviewStatus(interview.getInterviewStatus())
                .build();
    }

    public Interview toInterview(InterviewDto interviewDto) {
        Interview interview = new Interview();
        interview.setCandidate(candidateService.findCandidateByEmail(interviewDto.getCandidateEmailId()));
        interview.setScheduler(userService.findUserByEmailId(interviewDto.getSchedulerEmailId()));
        interview.setInterviewer(userService.findUserByEmailId(interviewDto.getInterviewerEmailId()));
        interview.setTalent(talentService.getTalent(interviewDto.getTalentId()));
        interview.setDateTime(interviewDto.getDateTime());
        interview.setMeetingLink(interviewDto.getMeetingLink());
        interview.setInterviewStatus(interviewDto.getInterviewStatus());
        return interview;
    }

    public List<InterviewDto> toDtos(List<Interview> interviews) {
        if (interviews == null) {
            return new ArrayList<>();
        }
        return interviews
                .stream()
                .map(interview -> toInterviewDto(interview))
                .collect(Collectors.toList());

    }
}
