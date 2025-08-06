package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports.ReportCardTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCardPgRepository extends ReactiveCrudRepository<ReportCardTable, Long> {
}

