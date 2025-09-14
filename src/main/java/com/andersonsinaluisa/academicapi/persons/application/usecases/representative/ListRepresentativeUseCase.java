package com.andersonsinaluisa.academicapi.persons.application.usecases.representative;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ListRepresentativeUseCase {
    @Autowired
    private RepresentativeRepository representativeRepository;
    private StudentRepresentiveRepository studentRepresentiveRepository;
    public Mono<PageResult<Representative>> execute(Pageable pageable) {
        return representativeRepository.all(pageable, new FilterCriteria());
    }

    public Mono<PageResult<Representative>> execute(long studentId){
        studentRepresentiveRepository
    }
}
