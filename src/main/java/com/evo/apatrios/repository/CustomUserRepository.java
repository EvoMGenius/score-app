package com.evo.apatrios.repository;

import com.evo.apatrios.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository extends JpaRepository<CustomUser, UUID>, QuerydslPredicateExecutor<CustomUser> {

    Optional<CustomUser> findByEmail(String email);
}
