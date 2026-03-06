package com.hirespace.hirespace_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hirespace.hirespace_api.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}