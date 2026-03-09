package com.hirespace.hirespace_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long jobId;
    private LocalDate savedDate;

    public SavedJob() {
    }

    public SavedJob(Long userId, Long jobId, LocalDate savedDate) {
        this.userId = userId;
        this.jobId = jobId;
        this.savedDate = savedDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public LocalDate getSavedDate() {
        return savedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setSavedDate(LocalDate savedDate) {
        this.savedDate = savedDate;
    }
}