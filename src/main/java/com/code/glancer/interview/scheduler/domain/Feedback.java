package com.code.glancer.interview.scheduler.domain;

import com.code.glancer.interview.scheduler.enums.InterviewStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    @OneToOne
    @JoinColumn(name = "interview_id", referencedColumnName = "id")
    private Interview interview;
    @Enumerated(EnumType.STRING)
    private InterviewStatus status;
    private String remarks;
    private Integer rating;

}
