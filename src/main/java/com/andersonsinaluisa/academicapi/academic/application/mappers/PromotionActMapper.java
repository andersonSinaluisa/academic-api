package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PromotionActInputDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.PromotionAct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PromotionActMapper {
    public PromotionAct toEntity(PromotionActInputDto dto) {
        return PromotionAct.builder()
                .courseId(dto.courseId)
                .academicYearId(dto.academicYearId)
                .generatedBy(dto.generatedBy)
                .generatedAt(LocalDate.now())
                .build();
    }

    public PromotionActDto toDto(PromotionAct act) {
        return PromotionActDto.builder()
                .id(act.id)
                .courseId(act.courseId)
                .academicYearId(act.academicYearId)
                .generatedBy(act.generatedBy)
                .generatedAt(act.generatedAt)
                .build();
    }
}
