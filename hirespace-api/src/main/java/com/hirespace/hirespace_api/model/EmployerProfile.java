package com.hirespace.hirespace_api.model;

import jakarta.persistence.*;

@Entity
public class EmployerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String companyName;

    @Column(length = 1000)
    private String description;

    private String website;
    private String industry;
    private String location;
    private String logoUrl;

    public EmployerProfile() {
    }

    public EmployerProfile(Long userId, String companyName, String description, String website, String industry, String location, String logoUrl) {
        this.userId = userId;
        this.companyName = companyName;
        this.description = description;
        this.website = website;
        this.industry = industry;
        this.location = location;
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}