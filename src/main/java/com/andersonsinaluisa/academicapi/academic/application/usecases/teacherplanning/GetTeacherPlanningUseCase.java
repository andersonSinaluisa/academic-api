package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherplanning;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PlanningMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetTeacherPlanningUseCase {

    private final TeacherPlanningRepository repository;

    public Mono<PlanningOutputDto> execute(Long id, Long teacherId) {
        return repository.findById(id)
                .filter(p -> p.teacherId.equals(teacherId))
                .map(PlanningMapper::toOutputDto);
    }
}
