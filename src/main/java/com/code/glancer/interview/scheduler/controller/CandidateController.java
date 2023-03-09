package com.code.glancer.interview.scheduler.controller;

import com.code.glancer.interview.scheduler.dto.CandidateDto;
import com.code.glancer.interview.scheduler.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public HttpEntity<CandidateDto> createCandidate(@RequestBody CandidateDto candidateDto) {
        CandidateDto candidate = candidateService.createCandidate(candidateDto);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    @GetMapping("{email}")
    public HttpEntity<CandidateDto> getCandidate(@PathVariable String email) {
        CandidateDto candidate = candidateService.getCandidate(email);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }
}
