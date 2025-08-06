package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository;

import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.RepresentativeTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RepresentativePgRepository extends ReactiveCrudRepository<RepresentativeTable,Long> {

    @Query("SELECT re.* FROM representative AS re join " +
            "student_representative sr on re.id = sr.representativeId" +
            "WHERE sr.studentId = :studentId")
    Flux<RepresentativeTable> findByStudentId(@Param("studentId") Long studentId);


    @Query("SELECT * FROM representative  LIMIT :limit OFFSET :offset ")
    Flux<RepresentativeTable> findAllByLimitAndOffset(
            @Param("limit") long limit,
            @Param("offset") long offset
            );

    @Query("UPDATE representative SET deleted=1 WHERE id=:id")
    Mono<Void> deleteById(
            @Param("id")
            Long Id);

}