package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.SavedJob;
import com.hirespace.hirespace_api.repository.SavedJobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/saved-jobs")
public class SavedJobController {

    private final SavedJobRepository savedJobRepository;

    public SavedJobController(SavedJobRepository savedJobRepository) {
        this.savedJobRepository = savedJobRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedJob>> getSavedJobsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(savedJobRepository.findByUserId(userId));
    }

    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> countSavedJobsByUser(@PathVariable Long userId) {
        long count = savedJobRepository.countByUserId(userId);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteSavedJob(@PathVariable Long id) {
    if (!savedJobRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    savedJobRepository.deleteById(id);
    return ResponseEntity.ok().build();
}
}