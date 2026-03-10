package com.hirespace.hirespace_api.repository;

import com.hirespace.hirespace_api.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    List<SavedJob> findByUserId(Long userId);
    long countByUserId(Long userId);
}