package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherAssignmentRepository {
    Mono<TeacherAssignment> save(TeacherAssignment assignment);
    Mono<TeacherAssignment> findById(Long id);
    Flux<TeacherAssignment> findByTeacherIdAndCourseId(Long teacherId, Long courseId);
    Mono<PageResult<TeacherAssignment>> findAll(Pageable pageable, FilterCriteria filters);
}
