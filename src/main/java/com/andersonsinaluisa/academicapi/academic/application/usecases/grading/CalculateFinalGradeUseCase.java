package com.andersonsinaluisa.academicapi.academic.application.usecases.grading;

import com.andersonsinaluisa.academicapi.academic.application.dtos.FinalGradeDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.FinalGrade;
import com.andersonsinaluisa.academicapi.academic.domain.entities.grading.Grade;
import com.andersonsinaluisa.academicapi.academic.domain.repository.GradeRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.client.ConfigSchoolClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateFinalGradeUseCase {

    private final GradeRepository gradeRepository;
    private final ConfigSchoolClient configSchoolClient;

    public Mono<FinalGradeDto> execute(Long studentId, Long subjectId, Long schoolYearId) {
        return configSchoolClient.getGradingSystem(schoolYearId)
                .flatMap(system -> Mono.zip(
                        configSchoolClient.getGradingTerms(system.id()).collectList(),
                        configSchoolClient.getAssessmentTypes(system.id()).collectList())
                        .flatMap(tuple -> calculateFinal(studentId, subjectId, schoolYearId, system.passingScore(), tuple.getT1(), tuple.getT2()))
                );
    }

    private Mono<FinalGradeDto> calculateFinal(Long studentId, Long subjectId, Long schoolYearId, Double passingScore,
                                               List<ConfigSchoolClient.GradingTermConfig> terms,
                                               List<ConfigSchoolClient.AssessmentTypeConfig> types) {
        return Flux.fromIterable(terms)
                .flatMap(term -> gradeRepository.findByStudentAndSubjectAndTerm(studentId, subjectId, term.id())
                        .collectList()
                        .map(grades -> computeTermAverage(grades, types) * term.weight()))
                .reduce(0.0, Double::sum)
                .map(total -> buildDto(studentId, subjectId, schoolYearId, passingScore, total));
    }

    private double computeTermAverage(List<Grade> grades, List<ConfigSchoolClient.AssessmentTypeConfig> types) {
        return types.stream()
                .mapToDouble(type -> {
                    List<Grade> typeGrades = grades.stream()
                            .filter(g -> type.id().equals(g.assessmentTypeId))
                            .toList();
                    if (typeGrades.isEmpty()) {
                        return 0d;
                    }
                    double avg = typeGrades.stream().mapToDouble(Grade::getScore).average().orElse(0d);
                    return avg * type.weight();
                }).sum();
    }

    private FinalGradeDto buildDto(Long studentId, Long subjectId, Long schoolYearId, Double passingScore, Double total) {
        FinalGrade domain = FinalGrade.builder()
                .studentId(studentId)
                .subjectId(subjectId)
                .schoolYearId(schoolYearId)
                .finalScore(total)
                .passed(total >= passingScore)
                .build();
        return FinalGradeDto.builder()
                .studentId(domain.getStudentId())
                .subjectId(domain.getSubjectId())
                .schoolYearId(domain.getSchoolYearId())
                .finalScore(domain.getFinalScore())
                .isPassed(domain.isPassed())
                .build();
    }
}
