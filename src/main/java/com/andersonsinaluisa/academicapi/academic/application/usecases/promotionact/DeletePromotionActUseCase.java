package com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact;

import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionActRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeletePromotionActUseCase {

    private final PromotionActRepository repository;

    public Mono<Void> execute(Long id) {
        return repository.deleteById(id);
    }
}
