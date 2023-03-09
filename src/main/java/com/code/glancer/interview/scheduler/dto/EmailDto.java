package com.code.glancer.interview.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {

    private String from;
    private String to;
    private List<String> toList;
    private String message;
    private String subject;

}