package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.StudentTable;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.TeacherTable;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.infrastructure.GenericR2dbcRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

@Repository
public class TeacherCustomRepository {
    private final GenericR2dbcRepository<TeacherTable> repository;

    public TeacherCustomRepository(DatabaseClient client) {
        this.repository = new GenericR2dbcRepository<>(
                client,
                "teacher",
                (row, meta) -> {
                    TeacherTable teacher = TeacherTable.builder()
                            .id(row.get("id",Long.class))
                            .firstName(row.get("first_name",String.class))
                            .lastName(row.get("last_name",String.class))
                            .address(row.get("address",String.class))
                            .image(row.get("image",String.class))
                            .phone(row.get("phone",String.class))
                            .gender(row.get("gender",String.class))
                            .uuidUser(row.get("uuid_user",String.class))
                            .identification(row.get("identification",String.class))
                            .deleted(Boolean.TRUE.equals(row.get("deleted", Boolean.class)))
                            .nationality(row.get("nationality",String.class))
                            .createdAt(row.get("created_at",LocalDate.class))
                            .deletedAt(row.get("created_at",LocalDate.class))
                            .birthDate(row.get("birth_date",LocalDate.class))
                            .tenantId(row.get("tenant_id",String.class))

                            .build();
                    return teacher;
                }
        );
    }
    public Flux<TeacherTable> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        return repository.findAllWithFilters(filters, limit, offset);
    }

    public Flux<TeacherTable> findAllWithLikeFilters(FilterCriteria filters, int limit, int offset) {
        return repository.findAllWithLikeFilters(filters, limit, offset);
    }

    public Mono<Long> countWithFilters(FilterCriteria filters) {
        return repository.countWithFilters(filters);
    }
    public Mono<Long> countWithLikeFilters(FilterCriteria filters) {
        return repository.countWithLikeFilters(filters);
    }


}
