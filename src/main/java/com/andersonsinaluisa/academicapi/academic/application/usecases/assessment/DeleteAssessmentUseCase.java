package com.andersonsinaluisa.academicapi.academic.application.usecases.assessment;

import com.andersonsinaluisa.academicapi.academic.domain.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteAssessmentUseCase {
    private final AssessmentRepository repository;

    public Mono<Void> execute(Long id) {
        return repository.deleteById(id);
    }
}
