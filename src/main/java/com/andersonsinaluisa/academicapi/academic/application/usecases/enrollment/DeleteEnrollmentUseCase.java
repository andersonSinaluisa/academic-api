package com.andersonsinaluisa.academicapi.academic.application.usecases.enrollment;

import com.andersonsinaluisa.academicapi.academic.domain.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteEnrollmentUseCase {
    private final EnrollmentRepository repository;

    public Mono<Void> execute(Long id) {
        return repository.deleteById(id);
    }
}
