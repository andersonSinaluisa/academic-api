package com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.EnrollmentDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.EnrollmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ListEnrollmentUseCase {
    private final EnrollmentRepository repository;

    public Flux<EnrollmentDto> execute() {
        return repository.findAll().map(EnrollmentMapper::fromDomainToDto);
    }
}
