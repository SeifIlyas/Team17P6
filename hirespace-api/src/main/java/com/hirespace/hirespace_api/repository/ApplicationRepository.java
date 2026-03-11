package com.hirespace.hirespace_api.repository;

import com.hirespace.hirespace_api.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserId(Long userId);
    long countByUserId(Long userId);
}
