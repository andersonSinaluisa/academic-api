package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom;

import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.RepresentativeTable;
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
public class RepresentativeCustomRepository {

    private final GenericR2dbcRepository<RepresentativeTable> repository;

    public RepresentativeCustomRepository(DatabaseClient client) {
        this.repository = new GenericR2dbcRepository<>(
                client,
                "student",
                (row, meta) -> {
                    RepresentativeTable s = RepresentativeTable.builder()
                            .id(row.get("id",Long.class))
                            .image(row.get("image",String.class))
                            .phone(row.get("phone",String.class))
                            .gender(row.get("gender",String.class))
                            .uuidUser(row.get("uuidUser",String.class))
                            .address(row.get("address",String.class))
                            .identification(row.get("identification",String.class))
                            .nacionality(row.get("nacionality",String.class))
                            .firtName(row.get("firstName",String.class))
                            .lastName(row.get("lastName",String.class))
                            .birthDate(row.get("birthDate",LocalDate.class))
                            .deleted(Objects.equals(row.get("deleted", Integer.class), 1))
                            .isActive(Objects.equals(row.get("isActive",Integer.class),1))
                            .tenantId(row.get("tenantId",String.class))
                            .build();
                    return s;
                }
        );
    }

    public Flux<RepresentativeTable> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        return repository.findAllWithFilters(filters, limit, offset);
    }

    public Mono<Long> countWithFilters(FilterCriteria filters) {
        return repository.countWithFilters(filters);
    }
}
