package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.behavior.BehaviorReportTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BehaviorReportPgRepository extends ReactiveCrudRepository<BehaviorReportTable, Long> {
}

