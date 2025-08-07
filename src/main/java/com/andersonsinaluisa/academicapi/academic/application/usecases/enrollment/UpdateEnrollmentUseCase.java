package com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.EnrollmentDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.EnrollmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.enrollment.Enrollment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateEnrollmentUseCase {
    private final EnrollmentRepository repository;

    public Mono<EnrollmentDto> execute(Long id, EnrollmentDto dto) {
        Enrollment enrollment = EnrollmentMapper.fromDtoToDomain(dto);
        enrollment.id = id;
        return repository.save(enrollment).map(EnrollmentMapper::fromDomainToDto);
    }
}
