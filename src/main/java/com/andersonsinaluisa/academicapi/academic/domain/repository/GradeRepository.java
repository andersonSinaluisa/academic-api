package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.Grade;
import reactor.core.publisher.Flux;

public interface GradeRepository {
    Flux<Grade> findByStudentAndSubjectAndTerm(Long studentId, Long subjectId, Long termId);
}
