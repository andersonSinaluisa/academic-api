package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.TeacherAssignmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegisterTeacherAssignmentUseCase {

    private final TeacherAssignmentRepository repository;

    public Mono<TeacherAssignmentOutputDto> execute(TeacherAssignmentInputDto dto) {
        return repository.save(TeacherAssignmentMapper.toDomain(dto))
                .map(TeacherAssignmentMapper::toOutputDto);
    }
}
