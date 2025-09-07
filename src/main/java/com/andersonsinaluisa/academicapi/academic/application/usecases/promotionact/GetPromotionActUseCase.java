package com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PromotionActMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionActRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetPromotionActUseCase {

    private final PromotionActRepository repository;
    private final PromotionActMapper mapper;

    public Mono<PromotionActDto> execute(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
}
