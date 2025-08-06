package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.application.dtos.ReportCardDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.ReportCardMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.ReportCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GenerateReportCardUseCase {

    private final ReportCardRepository repository;

    public Flux<ReportCardDto> execute() {
        return repository.findAll().map(ReportCardMapper::fromDomainToDto);
    }
}

