package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.JobseekerProfile;
import com.hirespace.hirespace_api.repository.JobseekerProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/jobseeker-profile")
public class JobseekerProfileController {

    private final JobseekerProfileRepository jobseekerProfileRepository;

    public JobseekerProfileController(JobseekerProfileRepository jobseekerProfileRepository) {
        this.jobseekerProfileRepository = jobseekerProfileRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<JobseekerProfile> getProfile(@PathVariable Long userId) {
        return jobseekerProfileRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<JobseekerProfile> updateProfile(@PathVariable Long userId, @RequestBody JobseekerProfile updatedProfile) {
        JobseekerProfile profile = jobseekerProfileRepository.findByUserId(userId)
                .orElse(new JobseekerProfile());

        profile.setUserId(userId);
        profile.setHeadline(updatedProfile.getHeadline());
        profile.setBio(updatedProfile.getBio());
        profile.setLocation(updatedProfile.getLocation());

        return ResponseEntity.ok(jobseekerProfileRepository.save(profile));
    }

    @PostMapping("/{userId}/upload-resume")
    public ResponseEntity<?> uploadResume(@PathVariable Long userId,
                                          @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }

        if (file.getOriginalFilename() == null || !file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Only PDF files are allowed");
        }

        try {
            String uploadDir = "uploads/resumes/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = "resume_user_" + userId + ".pdf";
            Path filePath = Paths.get(uploadDir + fileName);

            Files.write(filePath, file.getBytes());

            JobseekerProfile profile = jobseekerProfileRepository.findByUserId(userId)
                    .orElse(new JobseekerProfile());

            profile.setUserId(userId);
            profile.setResumeFileName(fileName);

            jobseekerProfileRepository.save(profile);

            return ResponseEntity.ok("Resume uploaded successfully");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading file");
        }
    }

    @GetMapping("/{userId}/resume")
    public ResponseEntity<?> downloadResume(@PathVariable Long userId) {

        return jobseekerProfileRepository.findByUserId(userId)
                .map(profile -> {

                    if (profile.getResumeFileName() == null || profile.getResumeFileName().isBlank()) {
                        return ResponseEntity.notFound().build();
                    }

                    try {
                        Path path = Paths.get("uploads/resumes/" + profile.getResumeFileName());

                        if (!Files.exists(path)) {
                            return ResponseEntity.notFound().build();
                        }

                        byte[] file = Files.readAllBytes(path);

                        return ResponseEntity.ok()
                                .header("Content-Type", "application/pdf")
                                .header("Content-Disposition", "attachment; filename=\"" + profile.getResumeFileName() + "\"")
                                .body(file);

                    } catch (IOException e) {
                        return ResponseEntity.internalServerError().build();
                    }

                })
                .orElse(ResponseEntity.notFound().build());
    }
}