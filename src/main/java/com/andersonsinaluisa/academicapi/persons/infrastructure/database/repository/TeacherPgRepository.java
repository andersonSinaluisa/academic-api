package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository;

import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.TeacherTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNullApi;

@Repository
public interface TeacherPgRepository extends ReactiveCrudRepository<TeacherTable,Long> {


    @Query("UPDATE teacher SET deleted=1 WHERE id =:teacherId ")
    Mono<Void> remove(@Param("teacherId") Long teacherId);

    @Query("SELECT * FROM teacher WHERE id=:id ")
    Mono<TeacherTable> findById(
            @Param("id") Long id
    );
}
