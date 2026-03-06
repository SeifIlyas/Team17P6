package com.hirespace.hirespace_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hirespace.hirespace_api.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}