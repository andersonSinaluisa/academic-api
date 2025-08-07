package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.assessment.Assessment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AssessmentRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.assessment.AssessmentTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.AssessmentPgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AssessmentRepositoryImpl implements AssessmentRepository {

    private final AssessmentPgRepository repository;

    @Override
    public Mono<Assessment> save(Assessment assessment) {
        return repository.save(toTable(assessment)).map(this::toDomain);
    }

    @Override
    public Flux<Assessment> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    @Override
    public Mono<Assessment> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    private AssessmentTable toTable(Assessment a) {
        return AssessmentTable.builder()
                .id(a.id)
                .subjectId(a.subjectId)
                .teacherId(a.teacherId)
                .academicYearId(a.academicYearId)
                .title(a.title)
                .description(a.description)
                .assessmentTypeId(a.assessmentTypeId)
                .maxScore(a.maxScore)
                .date(a.date)
                .build();
    }

    private Assessment toDomain(AssessmentTable t) {
        return Assessment.builder()
                .id(t.id)
                .subjectId(t.subjectId)
                .teacherId(t.teacherId)
                .academicYearId(t.academicYearId)
                .title(t.title)
                .description(t.description)
                .assessmentTypeId(t.assessmentTypeId)
                .maxScore(t.maxScore)
                .date(t.date)
                .build();
    }
}

