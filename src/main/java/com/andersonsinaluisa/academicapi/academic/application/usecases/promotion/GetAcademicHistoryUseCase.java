package com.andersonsinaluisa.academicapi.academic.application.usecases.promotion;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AcademicHistoryDto;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetAcademicHistoryUseCase {

    private final PromotionRepository repository;

    public Mono<AcademicHistoryDto> execute(Long studentId) {
        return repository.findByStudentId(studentId)
                .map(p -> AcademicHistoryDto.RecordDto.builder()
                        .academicYearId(p.academicYearId)
                        .course(null)
                        .finalAverage(p.finalAverage)
                        .status(p.status.name())
                        .build())
                .collectList()
                .map(list -> AcademicHistoryDto.builder()
                        .studentId(studentId)
                        .records(list)
                        .build());
    }
}
