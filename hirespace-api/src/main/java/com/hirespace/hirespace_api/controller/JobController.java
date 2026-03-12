package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.Job;
import com.hirespace.hirespace_api.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        return jobRepository.findById(id)
                .map(existingJob -> {
                    existingJob.setTitle(updatedJob.getTitle());
                    existingJob.setCompany(updatedJob.getCompany());
                    existingJob.setCategory(updatedJob.getCategory());
                    existingJob.setContactEmail(updatedJob.getContactEmail());
                    existingJob.setDescription(updatedJob.getDescription());
                    existingJob.setEmployerUserId(updatedJob.getEmployerUserId());
                    existingJob.setJobType(updatedJob.getJobType());
                    existingJob.setLocation(updatedJob.getLocation());
                    existingJob.setSalary(updatedJob.getSalary());

                    Job savedJob = jobRepository.save(existingJob);
                    return ResponseEntity.ok(savedJob);
                })
                .orElse(ResponseEntity.notFound().build());
    }


@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
    if (!jobRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    jobRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}
}

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }
}
