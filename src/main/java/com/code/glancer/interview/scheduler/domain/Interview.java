package com.code.glancer.interview.scheduler.domain;

import com.code.glancer.interview.scheduler.enums.InterviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "scheduler_id", referencedColumnName = "id")
    private User scheduler;
    @ManyToOne(optional = false)
    @JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    private User interviewer;
    private LocalDateTime dateTime;
    @ManyToOne(optional = false)
    @JoinColumn(name = "talent_id", referencedColumnName = "id")
    private Talent talent;
    @Enumerated(EnumType.STRING)
    private InterviewStatus interviewStatus;
    private String meetingLink;

}
