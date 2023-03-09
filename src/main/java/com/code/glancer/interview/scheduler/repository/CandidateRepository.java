package com.code.glancer.interview.scheduler.repository;

import com.code.glancer.interview.scheduler.domain.Candidate;
import com.code.glancer.interview.scheduler.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByEmail(String email);
}
