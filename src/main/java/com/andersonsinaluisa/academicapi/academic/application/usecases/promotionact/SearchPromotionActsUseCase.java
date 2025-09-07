package com.andersonsinaluisa.academicapi.academic.application.usecases.promotionact;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PromotionActMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionActRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SearchPromotionActsUseCase {

    private final PromotionActRepository repository;
    private final PromotionActMapper mapper;

    public Flux<PromotionActDto> execute(String courseId, String academicYearId) {
        return repository.findByCourseIdAndAcademicYearId(courseId, academicYearId)
                .map(mapper::toDto);
    }
}
