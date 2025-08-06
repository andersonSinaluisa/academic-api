package com.andersonsinaluisa.academicapi.persons.domain.repository;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface TeacherRepository {

    Mono<Teacher> create(Teacher teacher);

    Mono<Teacher> update(Teacher teacher);

    Mono<Page<Teacher>> findAll(Pageable pageable, FilterCriteria filters);

    Mono<Void> remove(Long id);

    Mono<Teacher> findById(Long Id);
}