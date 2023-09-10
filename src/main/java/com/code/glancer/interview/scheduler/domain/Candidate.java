package com.code.glancer.interview.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "candidate")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @Column(name = "emailId", unique = true, nullable = false)
    private String email;
    private String mobile;
    private Integer ctc;
    private Integer ectc;
    private String location;
    private String notice;
    private String resumeUrl;
}
