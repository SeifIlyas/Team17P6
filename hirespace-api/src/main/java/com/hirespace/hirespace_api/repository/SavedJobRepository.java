package com.hirespace.hirespace_api.repository;

import com.hirespace.hirespace_api.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {

    // get all saved jobs for a user
    List<SavedJob> findByUserId(Long userId);

    // check if job already saved
    Optional<SavedJob> findByUserIdAndJobId(Long userId, Long jobId);

    // delete saved job
    void deleteByUserIdAndJobId(Long userId, Long jobId);
}