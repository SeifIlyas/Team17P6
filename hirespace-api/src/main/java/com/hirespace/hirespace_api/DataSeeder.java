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
                "Help build and improve the HireSpace job portal UI and core features."
            ));

            jobRepository.save(new Job(
                "Front-End Developer",
                "Seif Digital",
                "Remote",
                "Software Engineering",
                "Part-time",
                "£15–£20/hr",
                "Work on modern web UI, responsive layout, and consistent styling."
            ));

            jobRepository.save(new Job(
                "Data Analyst Intern",
                "JobSphere",
                "Leeds",
                "Data / AI",
                "Internship",
                "£22k",
                "Support reporting, dashboards, and data cleaning for internal systems."
            ));
        }
    }
}