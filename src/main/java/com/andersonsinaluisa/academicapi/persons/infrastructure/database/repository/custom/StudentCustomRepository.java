package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom;

import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.StudentTable;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.infrastructure.GenericR2dbcRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

@Repository
public class StudentCustomRepository {

    private final GenericR2dbcRepository<StudentTable> repository;

    public StudentCustomRepository(DatabaseClient client) {
        this.repository = new GenericR2dbcRepository<>(
                client,
                "student",
                (row, meta) -> {
                    StudentTable s = StudentTable.builder()
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
                            .birthDate(row.get("birth_date", LocalDate.class))
                            .uuidParallel(row.get("uuid_parallel",String.class))
                            .uuidSchoolYear(row.get("uuid_school_year",String.class))
                            .tenantId(row.get("tenant_id",String.class))
                            .build();
                    return s;
                }
        );
    }

    public Flux<StudentTable> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        return repository.findAllWithFilters(filters, limit, offset);
    }

    public Mono<Long> countWithFilters(FilterCriteria filters) {
        return repository.countWithFilters(filters);
    }
}
