package com.code.glancer.interview.scheduler.service.impl;

import biweekly.parameter.ParticipationLevel;
import biweekly.property.Attendee;
import biweekly.property.Organizer;
import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.domain.Interview;
import com.code.glancer.interview.scheduler.domain.User;
import com.code.glancer.interview.scheduler.dto.CalenderDto;
import com.code.glancer.interview.scheduler.dto.EmailDto;
import com.code.glancer.interview.scheduler.dto.InterviewDto;
import com.code.glancer.interview.scheduler.mapper.InterviewMapper;
import com.code.glancer.interview.scheduler.repository.InterviewRepository;
import com.code.glancer.interview.scheduler.service.EmailService;
import com.code.glancer.interview.scheduler.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private InterviewMapper interviewMapper;
    @Autowired
    private EmailService emailService;

    @Override
    public InterviewDto scheduleInterview(InterviewDto interviewDto) {
        Interview interview = interviewMapper.toInterview(interviewDto);
        interviewRepository.save(interview);
        EmailDto emailDto = composeEmail(interview);
        CalenderDto calenderDto = composeCalender(interview);
       // emailService.sendEmail(emailDto);
        try {
            emailService.sendCalenderInvite(calenderDto);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        interviewDto = interviewMapper.toInterviewDto(interview);
        return interviewDto;
    }

    private CalenderDto composeCalender(Interview interview) {
        Candidate candidate = interview.getCandidate();
        return CalenderDto.builder()
                .subject(String.format("%s your Interview scheduled with AvenueCode", candidate.getName()))
                .description("Scheduling your Fist round of Technical Interview")
                .meetingLink(interview.getMeetingLink())
                .summary("Scheduling your Fist round of Technical Interview")
                .eventDateTime(interview.getDateTime())
                .organizer(new Organizer("AvenueCode", "info@avenuecode.com"))
                .attendees(getAttendees(interview))
                .build();


    }

    private List<Attendee> getAttendees(Interview interview) {
        Candidate candidate = interview.getCandidate();
        User scheduler = interview.getScheduler();
        User interviewer = interview.getInterviewer();

        List<Attendee> attendees = new ArrayList<>();

        Attendee candidateAttendee = new Attendee(candidate.getName(), candidate.getEmail());
        candidateAttendee.setParticipationLevel(ParticipationLevel.REQUIRED);
        attendees.add(candidateAttendee);

        Attendee schedulerAttendee = new Attendee(scheduler.getName(), scheduler.getEmailId());
        schedulerAttendee.setParticipationLevel(ParticipationLevel.OPTIONAL);
        attendees.add(schedulerAttendee);

        Attendee interviewerAttendee = new Attendee(interviewer.getName(), interviewer.getEmailId());
        interviewerAttendee.setParticipationLevel(ParticipationLevel.REQUIRED);
        attendees.add(interviewerAttendee);

        return attendees;

    }

    @Override
    public List<InterviewDto> getInterview(String schedulerEmail) {
        List<Interview> interviews = interviewRepository.getInterviewByScheduler_EmailId(schedulerEmail);
        List<InterviewDto> interviewDtos = interviewMapper.toDtos(interviews);
        return interviewDtos;
    }

    private EmailDto composeEmail(Interview interview) {
        return EmailDto.builder()
                .from(interview.getScheduler().getEmailId())
                .message(String.format("Your interview is scheduled at %s", interview.getDateTime()))
                .subject("Interview")
                .toList(getToEmails(interview))
                .build();

    }

    List<String> getToEmails(Interview interview) {
        List<String> emails = new ArrayList<>();
        emails.add(interview.getCandidate().getEmail());
        emails.add(interview.getScheduler().getEmailId());
        emails.add(interview.getInterviewer().getEmailId());
        return emails;
    }
}
