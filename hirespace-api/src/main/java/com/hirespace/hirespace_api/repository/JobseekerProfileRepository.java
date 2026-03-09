package com.hirespace.hirespace_api.repository;

import com.hirespace.hirespace_api.model.JobseekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JobseekerProfileRepository extends JpaRepository<JobseekerProfile, Long> {
    Optional<JobseekerProfile> findByUserId(Long userId);
}