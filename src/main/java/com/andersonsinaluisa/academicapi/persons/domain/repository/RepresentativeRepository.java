package com.andersonsinaluisa.academicapi.persons.domain.repository;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepresentativeRepository {

    Mono<Representative> create(Representative representative);

    Mono<Representative> update(Representative representative);

    Mono<Void> delete(Long id);

    Flux<Representative> getByStudent(Long studentId);

    Mono<Page<Representative>> all(Pageable pageable, FilterCriteria filterCriteria);


}
