package com.andersonsinaluisa.academicapi.academic.application.usecases.behavior;

import com.andersonsinaluisa.academicapi.academic.domain.repository.BehaviorReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteBehaviorReportUseCase {
    private final BehaviorReportRepository repository;

    public Mono<Void> execute(Long id) {
        return repository.deleteById(id);
    }
}
