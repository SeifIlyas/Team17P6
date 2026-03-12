package com.hirespace.hirespace_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hirespace.hirespace_api.model.Job;
import com.hirespace.hirespace_api.repository.JobRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final JobRepository jobRepository;

    public DataSeeder(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(String... args) {
        if (jobRepository.count() == 0) {
            jobRepository.save(new Job(
                "Software Engineer (Placement)",
                "HireSpace",
                "Bradford (Hybrid)",
                "Software Engineering",
                "Full-time",
                "£24k–£28k",
                "2026-03-10",
                "contact@hirespace.com",
                "Help build and improve the HireSpace job portal UI and core features.",
                1L
            ));

            jobRepository.save(new Job(
                "Front-End Developer",
                "Seif Digital",
                "Remote",
                "Software Engineering",
                "Part-time",
                "£15–£20/hr",
                "2026-03-10",
                "jobs@seifdigital.com",
                "Work on modern web UI, responsive layout, and consistent styling.",
                2L
            ));

            jobRepository.save(new Job(
                "Data Analyst Intern",
                "JobSphere",
                "Leeds",
                "Data / AI",
                "Internship",
                "£22k",
                "2026-03-10",
                "hr@jobsphere.com",
                "Support reporting, dashboards, and data cleaning for internal systems.",
                3L
            ));
        }
    }
}
