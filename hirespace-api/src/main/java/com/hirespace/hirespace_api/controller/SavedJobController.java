package com.hirespace.hirespace_api.controller;


import org.springframework.http.ResponseEntity;
import java.util.Optional;
import com.hirespace.hirespace_api.model.SavedJob;
import com.hirespace.hirespace_api.repository.SavedJobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/saved-jobs")
@CrossOrigin(origins = "*")
public class SavedJobController {

    private final SavedJobRepository savedJobRepository;

    public SavedJobController(SavedJobRepository savedJobRepository) {
        this.savedJobRepository = savedJobRepository;
    }

    // get saved jobs for user
    @GetMapping("/user/{userId}")
    public List<SavedJob> getSavedJobs(@PathVariable Long userId) {
        return savedJobRepository.findByUserId(userId);
    }

    // save job
    @PostMapping
    public SavedJob saveJob(@RequestBody SavedJob savedJob) {

        Optional<SavedJob> existing =
                savedJobRepository.findByUserIdAndJobId(savedJob.getUserId(), savedJob.getJobId());

        if (existing.isPresent()) {
            return existing.get();
        }

        return savedJobRepository.save(savedJob);
    }

   @DeleteMapping("/user/{userId}/job/{jobId}")
public ResponseEntity<?> deleteSavedJob(@PathVariable Long userId, @PathVariable Long jobId) {
    Optional<SavedJob> savedJob = savedJobRepository.findByUserIdAndJobId(userId, jobId);

    if (savedJob.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    savedJobRepository.delete(savedJob.get());
    return ResponseEntity.ok().build();
}
}