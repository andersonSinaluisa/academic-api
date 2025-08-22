package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionResultDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.Promotion;
import org.springframework.stereotype.Service;

@Service
public class PromotionMapper {
    public PromotionResultDto toDto(Promotion promotion) {
        return PromotionResultDto.builder()
                .studentId(promotion.studentId)
                .academicYearId(promotion.academicYearId)
                .finalAverage(promotion.finalAverage)
                .status(promotion.status.name())
                .generatedAt(promotion.generatedAt)
                .build();
    }
}
