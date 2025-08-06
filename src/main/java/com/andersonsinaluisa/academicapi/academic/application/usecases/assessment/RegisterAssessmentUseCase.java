package com.andersonsinaluisa.academicapi.academic.application.usecases.assessment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AssessmentDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.AssessmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegisterAssessmentUseCase {

    private final AssessmentRepository repository;

    public Mono<AssessmentDto> execute(AssessmentDto dto) {
        return repository.save(AssessmentMapper.fromDtoToDomain(dto))
                .map(AssessmentMapper::fromDomainToDto);
    }
}

