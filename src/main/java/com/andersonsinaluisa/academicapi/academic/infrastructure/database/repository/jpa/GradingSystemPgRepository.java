package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading.GradingSystemTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradingSystemPgRepository extends ReactiveCrudRepository<GradingSystemTable, Long> {
}

