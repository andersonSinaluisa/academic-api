package com.andersonsinaluisa.academicapi.academic.application.usecases.grading;

import com.andersonsinaluisa.academicapi.academic.application.dtos.ReportCardDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.ReportCardMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.ReportCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CalculateFinalGradesUseCase {

    private final ReportCardRepository repository;

    public Mono<ReportCardDto> execute(ReportCardDto dto) {
        return repository.save(ReportCardMapper.fromDtoToDomain(dto))
                .map(ReportCardMapper::fromDomainToDto);
    }
}

