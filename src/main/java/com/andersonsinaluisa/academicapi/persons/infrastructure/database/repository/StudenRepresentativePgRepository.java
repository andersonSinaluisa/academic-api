package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository;

import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.StudentRepresentativeTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudenRepresentativePgRepository extends ReactiveCrudRepository<StudentRepresentativeTable,Long> {

    @Query("UPDATE student_representative set deleted=1 WHERE studentId =:studentId" +
            "AND representativeId=:representativeId")
    Mono<Void> unasign(@Param("studentId") Long studentId,
                       @Param("representativeId") Long representativeId
                       );


}
