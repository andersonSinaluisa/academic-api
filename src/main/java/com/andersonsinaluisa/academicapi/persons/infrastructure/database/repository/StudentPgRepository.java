package com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.StudentTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentPgRepository extends ReactiveCrudRepository<StudentTable,Long> {

     @Query("SELECT * FROM student WHERE uuidCourse=:uuidCourse")
     Flux<StudentTable> findAllByUuidCourse(
             @Param("uuidCourse")
             String uuidCourse);

     @Query("SELECT * FROM student WHERE uuidParallel=:uuidParallel")
     Flux<StudentTable> findAllByUuidParallel(
             @Param("uuidParallel")
             String uuidParallel);
     @Query("SELECT * FROM student WHERE uuidSchoolYear=:uuidSchoolYear ")
     Flux<StudentTable> findAllByUuidSchoolYear(
             @Param("uuidSchoolYear")
             String uuidSchoolYear);

     @Query("SELECT * FROM student   LIMIT :limit OFFSET :offset")
     Flux<StudentTable> findAllByLimitAndOffset(
             @Param("limit") long limit,
             @Param("offset") long offset
             );
     @Query("UPDATE student SET deleted=1 WHERE id=:id ")
     Mono<Void> deleteById(
             @Param("id")
             Long Id);

     @Query("SELECT * FROM student WHERE id=:id")
     Mono<StudentTable> findById(@Param("id") Long id);
}
