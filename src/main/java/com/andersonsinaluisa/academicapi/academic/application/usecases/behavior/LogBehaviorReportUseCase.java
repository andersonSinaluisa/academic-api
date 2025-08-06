package com.andersonsinaluisa.academicapi.academic.application.usecases.behavior;

import com.andersonsinaluisa.academicapi.academic.application.dtos.BehaviorReportDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.BehaviorReportMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.BehaviorReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LogBehaviorReportUseCase {

    private final BehaviorReportRepository repository;

    public Mono<BehaviorReportDto> execute(BehaviorReportDto dto) {
        return repository.save(BehaviorReportMapper.fromDtoToDomain(dto))
                .map(BehaviorReportMapper::fromDomainToDto);
    }
}

