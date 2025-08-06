package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.reports.ReportCard;
import com.andersonsinaluisa.academicapi.academic.domain.repository.ReportCardRepository;
import com.andersonsinaluisa.academicapi.academic.domain.valueObjects.Score;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.reports.ReportCardTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.ReportCardPgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReportCardRepositoryImpl implements ReportCardRepository {

    private final ReportCardPgRepository repository;

    @Override
    public Mono<ReportCard> save(ReportCard reportCard) {
        return repository.save(toTable(reportCard)).map(this::toDomain);
    }

    @Override
    public Flux<ReportCard> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    private ReportCardTable toTable(ReportCard r) {
        return ReportCardTable.builder()
                .id(r.id)
                .academicYearId(r.academicYearId)
                .studentId(r.studentId)
                .averageScore(r.averageScore.getValue())
                .status(r.status)
                .build();
    }

    private ReportCard toDomain(ReportCardTable t) {
        return ReportCard.builder()
                .id(t.id)
                .academicYearId(t.academicYearId)
                .studentId(t.studentId)
                .averageScore(new Score(t.averageScore))
                .status(t.status)
                .build();
    }
}

