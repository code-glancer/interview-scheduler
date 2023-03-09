package com.code.glancer.interview.scheduler.repository;

import com.code.glancer.interview.scheduler.domain.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<Talent, Long> {

}
