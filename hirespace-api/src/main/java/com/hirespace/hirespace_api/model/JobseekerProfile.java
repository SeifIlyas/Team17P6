package com.hirespace.hirespace_api.model;

import jakarta.persistence.*;

@Entity
public class JobseekerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String headline;

    @Column(length = 1000)
    private String bio;

    private String location;
    private String resumeFileName;

    public JobseekerProfile() {
    }

    public JobseekerProfile(Long userId, String headline, String bio, String location) {
        this.userId = userId;
        this.headline = headline;
        this.bio = bio;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }
}