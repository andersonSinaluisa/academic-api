package com.andersonsinaluisa.academicapi.academic.application.usecases.behavior;

import com.andersonsinaluisa.academicapi.academic.application.dtos.BehaviorReportDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.BehaviorReportMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.behavior.BehaviorReport;
import com.andersonsinaluisa.academicapi.academic.domain.repository.BehaviorReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateBehaviorReportUseCase {
    private final BehaviorReportRepository repository;

    public Mono<BehaviorReportDto> execute(Long id, BehaviorReportDto dto) {
        BehaviorReport report = BehaviorReportMapper.fromDtoToDomain(dto);
        report.id = id;
        return repository.save(report).map(BehaviorReportMapper::fromDomainToDto);
    }
}
