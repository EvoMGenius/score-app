package com.evo.apatrios.repository;

import com.evo.apatrios.model.CustomUserBoughtAward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAwardRepository extends JpaRepository<CustomUserBoughtAward, UUID> {
}
