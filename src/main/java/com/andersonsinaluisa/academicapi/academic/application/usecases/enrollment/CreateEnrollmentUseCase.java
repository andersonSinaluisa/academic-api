package com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.EnrollmentDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.EnrollmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateEnrollmentUseCase {

    private final EnrollmentRepository repository;

    public Mono<EnrollmentDto> execute(EnrollmentDto dto) {
        return repository.save(EnrollmentMapper.fromDtoToDomain(dto))
                .map(EnrollmentMapper::fromDomainToDto);
    }
}

