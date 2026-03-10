package com.hirespace.hirespace_api.controller;

import com.hirespace.hirespace_api.model.EmployerProfile;
import com.hirespace.hirespace_api.repository.EmployerProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employer-profile")
public class EmployerProfileController {

    private final EmployerProfileRepository employerProfileRepository;

    public EmployerProfileController(EmployerProfileRepository employerProfileRepository) {
        this.employerProfileRepository = employerProfileRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EmployerProfile> getProfile(@PathVariable Long userId) {
        return employerProfileRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
public ResponseEntity<EmployerProfile> updateProfile(@PathVariable Long userId, @RequestBody EmployerProfile updatedProfile) {
    EmployerProfile profile = employerProfileRepository.findByUserId(userId)
            .orElse(new EmployerProfile());

    profile.setUserId(userId);
    profile.setCompanyName(updatedProfile.getCompanyName());
    profile.setDescription(updatedProfile.getDescription());
    profile.setWebsite(updatedProfile.getWebsite());
    profile.setIndustry(updatedProfile.getIndustry());
    profile.setLocation(updatedProfile.getLocation());
    profile.setLogoUrl(updatedProfile.getLogoUrl());

    return ResponseEntity.ok(employerProfileRepository.save(profile));
}

}