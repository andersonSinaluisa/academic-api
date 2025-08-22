package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.application.dtos.ReportCardDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.ReportCardMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.ReportCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetReportCardUseCase {

    private final ReportCardRepository repository;

    public Mono<ReportCardDto> execute(Long studentId, String academicYearId) {
        return repository.findByStudentIdAndAcademicYearId(studentId, academicYearId)
                .map(ReportCardMapper::fromDomainToDto);
    }
}

