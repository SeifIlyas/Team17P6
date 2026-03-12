package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.Application;
import com.hirespace.hirespace_api.model.Job;
import com.hirespace.hirespace_api.repository.ApplicationRepository;
import com.hirespace.hirespace_api.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ApplicationController(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createApplication(@RequestBody Application application) {
        boolean alreadyExists = applicationRepository.existsByUserIdAndJobId(
                application.getUserId(),
                application.getJobId()
        );

        if (alreadyExists) {
            return ResponseEntity.badRequest().body("You have already applied for this job");
        }

        if (application.getStatus() == null || application.getStatus().isBlank()) {
            application.setStatus("PENDING");
        }

        application.setAppliedDate(LocalDate.now());
        application.setUpdatedAt(LocalDateTime.now());

        Application savedApplication = applicationRepository.save(application);
        return ResponseEntity.ok(savedApplication);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getApplicationsByUser(@PathVariable Long userId) {
        List<Application> applications = applicationRepository.findByUserId(userId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Application application : applications) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("applicationId", application.getId());
            item.put("jobId", application.getJobId());
            item.put("userId", application.getUserId());
            item.put("status", application.getStatus());
            item.put("appliedDate", application.getAppliedDate());
            item.put("updatedAt", application.getUpdatedAt());

            Job job = jobRepository.findById(application.getJobId()).orElse(null);

            if (job != null) {
                item.put("title", job.getTitle());
                item.put("company", job.getCompany());
                item.put("location", job.getLocation());
                item.put("description", job.getDescription());
                item.put("jobType", job.getJobType());
                item.put("salary", job.getSalary());
            } else {
                item.put("title", "Job not found");
                item.put("company", "");
                item.put("location", "");
                item.put("description", "");
                item.put("jobType", "");
                item.put("salary", "");
            }

            response.add(item);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> countApplicationsByUser(@PathVariable Long userId) {
        long count = applicationRepository.countByUserId(userId);
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}")
public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application updatedApplication) {
    return applicationRepository.findById(id)
            .map(application -> {
                application.setStatus(updatedApplication.getStatus());
                application.setUpdatedAt(LocalDateTime.now());
                return ResponseEntity.ok(applicationRepository.save(application));
            })
            .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        if (!applicationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        applicationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}