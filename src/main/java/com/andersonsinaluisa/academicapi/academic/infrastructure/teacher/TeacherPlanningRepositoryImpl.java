package com.andersonsinaluisa.academicapi.academic.infrastructure.teacher;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherPlanning;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherPlanningRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory implementation for teacher plannings.
 */
@Service
public class TeacherPlanningRepositoryImpl implements TeacherPlanningRepository {

    private final Map<Long, TeacherPlanning> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Mono<TeacherPlanning> save(TeacherPlanning planning) {
        if (planning.id == null) {
            planning.id = sequence.getAndIncrement();
            planning.createdAt = LocalDateTime.now();
        }
        store.put(planning.id, planning);
        return Mono.just(planning);
    }

    @Override
    public Mono<TeacherPlanning> findById(Long id) {
        return Mono.justOrEmpty(store.get(id));
    }

    @Override
    public Flux<TeacherPlanning> search(Long teacherId, Long subjectId, Long courseId) {
        return Flux.fromStream(store.values().stream()
                .filter(p -> (teacherId == null || teacherId.equals(p.teacherId))
                        && (subjectId == null || subjectId.equals(p.subjectId))
                        && (courseId == null || courseId.equals(p.courseId))));
    }
}
