package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.custom;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment.EnrollmentTable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class EnrollmentCustomRepository {
    private final DatabaseClient client;

    public EnrollmentCustomRepository(DatabaseClient client) {
        this.client = client;
    }

    public Flux<EnrollmentTable> findByStatus(String status) {
        return client.sql("SELECT * FROM enrollment WHERE status = :status")
                .bind("status", status)
                .map((row, meta) -> EnrollmentTable.builder()
                        .id(row.get("id", Long.class))
                        .studentId(row.get("student_id", Long.class))
                        .courseId(row.get("course_id", String.class))
                        .academicYearId(row.get("academic_year_id", String.class))
                        .enrollmentDate(row.get("enrollment_date", java.time.LocalDate.class))
                        .status(row.get("status", String.class))
                        .build())
                .all();
    }
}

