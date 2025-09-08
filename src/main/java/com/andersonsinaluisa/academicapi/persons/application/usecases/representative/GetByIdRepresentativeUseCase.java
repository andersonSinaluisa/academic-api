package com.andersonsinaluisa.academicapi.persons.application.usecases.representative;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetByIdRepresentativeUseCase {
    @Autowired
    private RepresentativeRepository representativeRepository;

    public Mono<Representative> execute(Long id) {
        return representativeRepository.getById(id);
    }
}
