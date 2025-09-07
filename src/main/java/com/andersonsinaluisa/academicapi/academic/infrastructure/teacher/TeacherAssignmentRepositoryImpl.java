package com.andersonsinaluisa.academicapi.academic.infrastructure.teacher;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory implementation used for prototyping.
 */
@Service
public class TeacherAssignmentRepositoryImpl implements TeacherAssignmentRepository {

    private final Map<Long, TeacherAssignment> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Mono<TeacherAssignment> save(TeacherAssignment assignment) {
        if (assignment.id == null) {
            assignment.id = sequence.getAndIncrement();
            assignment.createdAt = LocalDateTime.now();
        }
        store.put(assignment.id, assignment);
        return Mono.just(assignment);
    }

    @Override
    public Mono<TeacherAssignment> findById(Long id) {
        return Mono.justOrEmpty(store.get(id));
    }

    @Override
    public Flux<TeacherAssignment> findByTeacherIdAndCourseId(Long teacherId, Long courseId) {
        return Flux.fromStream(store.values().stream()
                .filter(a -> (teacherId == null || teacherId.equals(a.teacherId))
                        && (courseId == null || courseId.equals(a.courseId))));
    }
}
