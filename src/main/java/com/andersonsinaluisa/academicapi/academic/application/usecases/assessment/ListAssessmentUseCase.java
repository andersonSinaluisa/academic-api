package com.andersonsinaluisa.academicapi.academic.application.usecases.assessment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AssessmentDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.AssessmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ListAssessmentUseCase {
    private final AssessmentRepository repository;

    public Flux<AssessmentDto> execute() {
        return repository.findAll().map(AssessmentMapper::fromDomainToDto);
    }
}
