package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherplanning;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PlanningMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SearchTeacherPlanningsUseCase {

    private final TeacherPlanningRepository repository;

    public Flux<PlanningOutputDto> execute(Long teacherId, Long subjectId, Long courseId) {
        return repository.search(teacherId, subjectId, courseId)
                .map(PlanningMapper::toOutputDto);
    }
}
