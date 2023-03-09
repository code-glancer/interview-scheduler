package com.code.glancer.interview.scheduler.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "talent")
@Data
public class Talent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String requirements;
    private String projectName;
    private String managerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobLocation;

}
