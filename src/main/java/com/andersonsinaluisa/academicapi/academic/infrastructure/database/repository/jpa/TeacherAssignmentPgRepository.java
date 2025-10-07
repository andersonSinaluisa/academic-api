package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment.TeacherAssignmentTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TeacherAssignmentPgRepository extends ReactiveCrudRepository<TeacherAssignmentTable, Long> {
    Flux<TeacherAssignmentTable> findAllByTeacherId(Long teacherId);

    Flux<TeacherAssignmentTable> findAllByTeacherIdAndCourseId(Long teacherId, String courseId);
}
