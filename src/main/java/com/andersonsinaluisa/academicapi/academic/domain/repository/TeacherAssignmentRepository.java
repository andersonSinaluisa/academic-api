package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherAssignmentRepository {
    Mono<TeacherAssignment> save(TeacherAssignment assignment);
    Mono<TeacherAssignment> findById(Long id);
    Flux<TeacherAssignment> findByTeacherIdAndCourseId(Long teacherId, Long courseId);
}
