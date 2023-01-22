package com.evo.apatrios.repository;

import com.evo.apatrios.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository extends JpaRepository<CustomUser, UUID> {

    Optional<CustomUser> findByEmail(String email);
}
