package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.assessment.AssessmentTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentPgRepository extends ReactiveCrudRepository<AssessmentTable, Long> {
}

