package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.enrollment.Enrollment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.EnrollmentRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment.EnrollmentTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.EnrollmentPgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final EnrollmentPgRepository repository;

    @Override
    public Mono<Enrollment> save(Enrollment enrollment) {
        return repository.save(toTable(enrollment)).map(this::toDomain);
    }

    @Override
    public Flux<Enrollment> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    private EnrollmentTable toTable(Enrollment e) {
        return EnrollmentTable.builder()
                .id(e.id)
                .studentId(e.studentId)
                .courseId(e.courseId)
                .academicYearId(e.academicYearId)
                .enrollmentDate(e.enrollmentDate)
                .status(e.status)
                .build();
    }

    private Enrollment toDomain(EnrollmentTable t) {
        return Enrollment.builder()
                .id(t.id)
                .studentId(t.studentId)
                .courseId(t.courseId)
                .academicYearId(t.academicYearId)
                .enrollmentDate(t.enrollmentDate)
                .status(t.status)
                .build();
    }
}

