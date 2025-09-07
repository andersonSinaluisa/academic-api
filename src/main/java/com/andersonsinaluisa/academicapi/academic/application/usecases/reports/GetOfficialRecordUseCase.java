package com.andersonsinaluisa.academicapi.academic.application.usecases.reports;

import com.andersonsinaluisa.academicapi.academic.application.dtos.OfficialRecordDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.reports.ReportCard;
import com.andersonsinaluisa.academicapi.academic.domain.repository.ReportCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetOfficialRecordUseCase {

    private final ReportCardRepository repository;

    public Mono<OfficialRecordDto> execute(Long studentId) {
        return repository.findByStudentId(studentId)
                .map(this::toRecord)
                .collectList()
                .map(list -> OfficialRecordDto.builder()
                        .studentId(studentId)
                        .fullName("Unknown")
                        .records(list)
                        .build());
    }

    private OfficialRecordDto.RecordDto toRecord(ReportCard rc) {
        return OfficialRecordDto.RecordDto.builder()
                .academicYearId(rc.academicYearId)
                .course("N/A")
                .averageScore(rc.averageScore.getValue())
                .status(rc.status)
                .build();
    }
}

