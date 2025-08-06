package com.andersonsinaluisa.academicapi.persons.domain.repository;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository {

    Flux<Student> getByCourse(String courseId);

    Flux<Student> getByParallel(String parallelId);

    Flux<Student> getBySchoolYear(String schoolYearId);

    Mono<Page<Student>> findAll(Pageable pageable, FilterCriteria filters);
    Mono<Student> create(Student student);

    Mono<Student> update(Long id, Student student);

    Mono<Void> delete(Long id);

    Mono<Student> getById(Long id);
}
