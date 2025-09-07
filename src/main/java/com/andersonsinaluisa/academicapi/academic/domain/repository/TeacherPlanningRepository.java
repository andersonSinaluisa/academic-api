package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherPlanning;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherPlanningRepository {
    Mono<TeacherPlanning> save(TeacherPlanning planning);
    Mono<TeacherPlanning> findById(Long id);
    Flux<TeacherPlanning> search(Long teacherId, Long subjectId, Long courseId);
}
