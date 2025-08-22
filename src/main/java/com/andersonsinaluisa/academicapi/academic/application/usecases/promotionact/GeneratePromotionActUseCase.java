package com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActInputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PromotionActMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionActRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeneratePromotionActUseCase {

    private final PromotionActRepository repository;
    private final PromotionActMapper mapper;

    public Mono<PromotionActDto> execute(PromotionActInputDto dto) {
        return repository.save(mapper.toEntity(dto))
                .map(mapper::toDto);
    }
}
