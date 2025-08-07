package com.andersonsinaluisa.academicapi.persons.application.usecases.representative;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ListRepresentativeUseCase {
    private RepresentativeRepository representativeRepository;

    public Mono<Page<Representative>> execute(Pageable pageable) {
        return representativeRepository.all(pageable, new FilterCriteria());
    }
}
