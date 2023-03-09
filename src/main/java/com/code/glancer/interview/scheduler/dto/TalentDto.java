package com.code.glancer.interview.scheduler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TalentDto {

    private long id;
    private String name;
    private String requirements;
    private String projectName;
    private String managerName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String jobLocation;
}
