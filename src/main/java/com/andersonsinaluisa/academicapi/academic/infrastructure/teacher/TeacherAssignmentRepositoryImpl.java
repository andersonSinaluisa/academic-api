package com.andersonsinaluisa.academicapi.academic.infrastructure.teacher;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment.TeacherAssignmentTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.mappers.TeacherAssignmentTableMapper;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.custom.TeacherAssignmentCustomRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.TeacherAssignmentPgRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAssignmentRepositoryImpl implements TeacherAssignmentRepository {

    private final TeacherAssignmentPgRepository repository;
    private final TeacherAssignmentCustomRepository customRepository;

    @Override
    public Mono<TeacherAssignment> save(TeacherAssignment assignment) {
        if (assignment.createdAt == null) {
            assignment.createdAt = LocalDateTime.now();
        }
        TeacherAssignmentTable table = TeacherAssignmentTableMapper.fromDomainToPersistence(assignment);
        return repository.save(table)
                .map(TeacherAssignmentTableMapper::fromPersistenceToDomain)
                .map(saved -> {
                    if (saved.createdAt == null) {
                        saved.createdAt = assignment.createdAt;
                    }
                    return saved;
                });
    }

    @Override
    public Mono<TeacherAssignment> findById(Long id) {
        return repository.findById(id)
                .map(TeacherAssignmentTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Flux<TeacherAssignment> findByTeacherIdAndCourseId(Long teacherId, Long courseId) {
        Flux<TeacherAssignmentTable> results = courseId == null
                ? repository.findAllByTeacherId(teacherId)
                : repository.findAllByTeacherIdAndCourseId(teacherId, String.valueOf(courseId));

        return results.map(TeacherAssignmentTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<PageResult<TeacherAssignment>> findAll(Pageable pageable, FilterCriteria filters) {
        int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        Mono<List<TeacherAssignment>> content = customRepository
                .findAllWithFilters(filters, pageSize, offset)
                .map(TeacherAssignmentTableMapper::fromPersistenceToDomain)
                .collectList();

        Mono<Long> count = customRepository.countWithFilters(filters);

        return Mono.zip(content, count)
                .map(tuple -> {
                    List<TeacherAssignment> assignments = tuple.getT1();
                    long total = tuple.getT2();
                    long totalPages = pageSize == 0 ? 0 : (long) Math.ceil((double) total / pageSize);
                    return new PageResult<>(assignments, total, pageNumber, pageSize, totalPages);
                });
    }
}
