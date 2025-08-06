package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.grading.GradeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GradePgRepository extends ReactiveCrudRepository<GradeEntity, Long> {
    Flux<GradeEntity> findByStudentIdAndSubjectIdAndTermId(Long studentId, Long subjectId, Long termId);
}
