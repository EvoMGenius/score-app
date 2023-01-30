package com.evo.apatrios.repository;

import com.evo.apatrios.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface AwardRepository extends JpaRepository<Award, UUID>, QuerydslPredicateExecutor<Award> {
}
