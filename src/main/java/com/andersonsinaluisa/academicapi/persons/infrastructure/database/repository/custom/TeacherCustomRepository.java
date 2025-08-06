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
                            .firtName(row.get("firstName",String.class))
                            .lastName(row.get("lastName",String.class))
                            .address(row.get("address",String.class))
                            .image(row.get("image",String.class))
                            .phone(row.get("phone",String.class))
                            .gender(row.get("gender",String.class))
                            .uuidUser(row.get("uuidUser",String.class))
                            .identification(row.get("identificacion",String.class))
                            .deleted(Objects.equals(row.get("deleted", Integer.class), 1))
                            .nacionality(row.get("nacionality",String.class))
                            .createdAt(row.get("createdAt",LocalDate.class))
                            .deletedAt(row.get("deletedAt",LocalDate.class))
                            .birthDate(row.get("birhtData",LocalDate.class))
                            .tenantId(row.get("tenantId",String.class))

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
        return repository.countWithLikeFilters(filters);
    }
    public Mono<Long> countWithLikeFilters(FilterCriteria filters) {
        return repository.countWithLikeFilters(filters);
    }


}
