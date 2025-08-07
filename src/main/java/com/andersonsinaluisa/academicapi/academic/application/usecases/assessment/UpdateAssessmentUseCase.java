package com.andersonsinaluisa.academicapi.academic.application.usecases.assessment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AssessmentDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.AssessmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.assessment.Assessment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateAssessmentUseCase {
    private final AssessmentRepository repository;

    public Mono<AssessmentDto> execute(Long id, AssessmentDto dto) {
        Assessment assessment = AssessmentMapper.fromDtoToDomain(dto);
        assessment.id = id;
        return repository.save(assessment)
                .map(AssessmentMapper::fromDomainToDto);
    }
}
