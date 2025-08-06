package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.Grade;
import com.andersonsinaluisa.academicapi.academic.domain.repository.GradeRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading.GradeEntity;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.GradePgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GradeRepositoryImpl implements GradeRepository {

    private final GradePgRepository repository;

    @Override
    public Flux<Grade> findByStudentAndSubjectAndTerm(Long studentId, Long subjectId, Long termId) {
        return repository.findByStudentIdAndSubjectIdAndTermId(studentId, subjectId, termId)
                .map(this::toDomain);
    }

    private GradeEntity toEntity(Grade g) {
        return GradeEntity.builder()
                .id(g.id)
                .assessmentId(g.assessmentId)
                .studentId(g.studentId)
                .subjectId(g.subjectId)
                .termId(g.termId)
                .assessmentTypeId(g.assessmentTypeId)
                .score(g.score)
                .feedback(g.feedback)
                .build();
    }

    private Grade toDomain(GradeEntity e) {
        return Grade.builder()
                .id(e.id)
                .assessmentId(e.assessmentId)
                .studentId(e.studentId)
                .subjectId(e.subjectId)
                .termId(e.termId)
                .assessmentTypeId(e.assessmentTypeId)
                .score(e.score)
                .feedback(e.feedback)
                .build();
    }
}
