package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.GradingSystem;
import com.andersonsinaluisa.academicapi.academic.domain.repository.GradingSystemRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading.GradingSystemTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.GradingSystemPgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GradingSystemRepositoryImpl implements GradingSystemRepository {

    private final GradingSystemPgRepository repository;

    @Override
    public Mono<GradingSystem> save(GradingSystem gradingSystem) {
        return repository.save(toTable(gradingSystem)).map(this::toDomain);
    }

    @Override
    public Flux<GradingSystem> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    private GradingSystemTable toTable(GradingSystem g) {
        return GradingSystemTable.builder()
                .id(g.id)
                .name(g.name)
                .description(g.description)
                .numberOfTerms(g.numberOfTerms)
                .passingScore(g.passingScore)
                .build();
    }

    private GradingSystem toDomain(GradingSystemTable t) {
        return GradingSystem.builder()
                .id(t.id)
                .name(t.name)
                .description(t.description)
                .numberOfTerms(t.numberOfTerms)
                .passingScore(t.passingScore)
                .build();
    }
}

