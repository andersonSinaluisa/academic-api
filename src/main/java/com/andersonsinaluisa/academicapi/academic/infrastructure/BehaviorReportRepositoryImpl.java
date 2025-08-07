package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.behavior.BehaviorReport;
import com.andersonsinaluisa.academicapi.academic.domain.repository.BehaviorReportRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.behavior.BehaviorReportTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.BehaviorReportPgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BehaviorReportRepositoryImpl implements BehaviorReportRepository {

    private final BehaviorReportPgRepository repository;

    @Override
    public Mono<BehaviorReport> save(BehaviorReport report) {
        return repository.save(toTable(report)).map(this::toDomain);
    }

    @Override
    public Flux<BehaviorReport> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    @Override
    public Mono<BehaviorReport> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    private BehaviorReportTable toTable(BehaviorReport b) {
        return BehaviorReportTable.builder()
                .id(b.id)
                .studentId(b.studentId)
                .termId(b.termId)
                .score(b.score)
                .observations(b.observations)
                .build();
    }

    private BehaviorReport toDomain(BehaviorReportTable t) {
        return BehaviorReport.builder()
                .id(t.id)
                .studentId(t.studentId)
                .termId(t.termId)
                .score(t.score)
                .observations(t.observations)
                .build();
    }
}

