package com.code.glancer.interview.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

    private Long id;
    private String name;
    private String address;
    private String email;
    private String mobile;
    private Integer ctc;
    private Integer ectc;
    private String location;
    private String notice;
    private String resumeUrl;
}
