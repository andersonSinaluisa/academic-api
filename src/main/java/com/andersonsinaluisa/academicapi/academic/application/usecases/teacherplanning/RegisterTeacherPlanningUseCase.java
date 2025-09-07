package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherplanning;

import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.PlanningOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.PlanningMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegisterTeacherPlanningUseCase {

    private final TeacherPlanningRepository repository;

    public Mono<PlanningOutputDto> execute(PlanningInputDto dto) {
        return repository.save(PlanningMapper.toDomain(dto))
                .map(PlanningMapper::toOutputDto);
    }
}
