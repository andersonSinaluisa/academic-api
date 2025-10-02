package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputManyDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputManyDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.TeacherAssignmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterTeacherAssignmentUseCase {

    private final TeacherAssignmentRepository repository;

    public Mono<TeacherAssignmentOutputDto> execute(TeacherAssignmentInputDto dto) {
        return repository.save(TeacherAssignmentMapper.toDomain(dto))
                .map(TeacherAssignmentMapper::toOutputDto);
    }


    public Mono<List<TeacherAssignmentOutputDto>> execute(TeacherAssignmentInputManyDto dto) {
        var list = TeacherAssignmentMapper.fromManyToDomain(dto);

        return Flux.fromIterable(list)
                .flatMap(assignment ->
                        repository.save(assignment)
                                .map(TeacherAssignmentMapper::toOutputDto)
                )
                .collectList();
    }
}
