package com.hirespace.hirespace_api.model;

import jakarta.persistence.*;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String category;
    private String jobType;
    private String salary;
    private Long employerUserId;

    @Column(length = 4000)
    private String description;

    public Job() {}

    public Job(String title, String company, String location, String category, String jobType, String salary, String description, Long employerUserId) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.category = category;
        this.jobType = jobType;
        this.salary = salary;
        this.description = description;
        this.employerUserId = employerUserId;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public String getJobType() { return jobType; }
    public String getSalary() { return salary; }
    public String getDescription() { return description; }
    public Long getEmployerUserId() { return employerUserId;}


    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCompany(String company) { this.company = company; }
    public void setLocation(String location) { this.location = location; }
    public void setCategory(String category) { this.category = category; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    public void setSalary(String salary) { this.salary = salary; }
    public void setDescription(String description) { this.description = description; }
    public void setEmployerUserId(Long employerUserId) { this.employerUserId = employerUserId; }
}