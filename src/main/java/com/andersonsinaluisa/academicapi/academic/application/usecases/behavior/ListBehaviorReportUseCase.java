package com.andersonsinaluisa.academicapi.academic.application.usecases.behavior;

import com.andersonsinaluisa.academicapi.academic.application.dtos.BehaviorReportDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.BehaviorReportMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.BehaviorReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ListBehaviorReportUseCase {
    private final BehaviorReportRepository repository;

    public Flux<BehaviorReportDto> execute() {
        return repository.findAll().map(BehaviorReportMapper::fromDomainToDto);
    }
}
