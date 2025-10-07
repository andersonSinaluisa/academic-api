package com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.custom;

import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment.TeacherAssignmentTable;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class TeacherAssignmentCustomRepository {

    private final DatabaseClient client;

    public TeacherAssignmentCustomRepository(DatabaseClient client) {
        this.client = client;
    }

    public Flux<TeacherAssignmentTable> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM teacher_assignment WHERE 1=1");
        filters.asMap().forEach((key, value) -> {
            if (value == null) {
                sql.append(" AND ").append(key).append(" IS NULL");
            } else {
                sql.append(" AND ").append(key)
                        .append(" = '").append(value.replace("'", "''")).append("'");
            }
        });
        sql.append(" ORDER BY created_at DESC NULLS LAST, id DESC");
        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        return client.sql(sql.toString())
                .map((row, meta) -> TeacherAssignmentTable.builder()
                        .id(row.get("id", Long.class))
                        .teacherId(row.get("teacher_id", Long.class))
                        .courseId(row.get("course_id", String.class))
                        .subjectId(row.get("subject_id", String.class))
                        .academicYearId(row.get("academic_year_id", Long.class))
                        .createdAt(row.get("created_at", LocalDateTime.class))
                        .build())
                .all();
    }

    public Mono<Long> countWithFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*)::bigint as total FROM teacher_assignment WHERE 1=1");
        filters.asMap().forEach((key, value) -> {
            if (value == null) {
                sql.append(" AND ").append(key).append(" IS NULL");
            } else {
                sql.append(" AND ").append(key)
                        .append(" = '").append(value.replace("'", "''")).append("'");
            }
        });

        return client.sql(sql.toString())
                .map((row, meta) -> {
                    Object total = row.get("total");
                    return (total instanceof Number) ? ((Number) total).longValue() : 0L;
                })
                .one();
    }
}
