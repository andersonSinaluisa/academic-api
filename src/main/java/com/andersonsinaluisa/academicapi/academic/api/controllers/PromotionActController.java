package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActInputDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact.DeletePromotionActUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact.GeneratePromotionActUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact.GetPromotionActUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact.SearchPromotionActsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/promotion-acts")
@RequiredArgsConstructor
public class PromotionActController {

    private final GeneratePromotionActUseCase generateUseCase;
    private final GetPromotionActUseCase getUseCase;
    private final SearchPromotionActsUseCase searchUseCase;
    private final DeletePromotionActUseCase deleteUseCase;

    @PostMapping
    public Mono<PromotionActDto> generate(@Valid @RequestBody PromotionActInputDto dto) {
        return generateUseCase.execute(dto);
    }

    @GetMapping("/{id}")
    public Mono<PromotionActDto> get(@PathVariable Long id) {
        return getUseCase.execute(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public Flux<PromotionActDto> search(@RequestParam(required = false) String courseId,
                                        @RequestParam(required = false) String academicYearId) {
        return searchUseCase.execute(courseId, academicYearId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteUseCase.execute(id);
    }
}
