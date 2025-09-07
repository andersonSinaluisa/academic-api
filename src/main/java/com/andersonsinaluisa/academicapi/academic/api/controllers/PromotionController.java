package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionRequestDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionResultDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.promotion.DeterminePromotionUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final DeterminePromotionUseCase determineUseCase;

    @PostMapping
    public Mono<PromotionResultDto> determine(@Valid @RequestBody PromotionRequestDto dto) {
        return determineUseCase.execute(dto);
    }
}
