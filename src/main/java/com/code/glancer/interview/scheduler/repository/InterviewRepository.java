package com.code.glancer.interview.scheduler.repository;

import com.code.glancer.interview.scheduler.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> getInterviewByScheduler_EmailId(String schedulerEmail);
}
