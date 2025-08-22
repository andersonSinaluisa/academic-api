package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.TeacherAssignmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetTeacherAssignmentUseCase {

    private final TeacherAssignmentRepository repository;

    public Mono<TeacherAssignmentOutputDto> execute(Long id, Long teacherId) {
        return repository.findById(id)
                .filter(a -> a.teacherId.equals(teacherId))
                .map(TeacherAssignmentMapper::toOutputDto);
    }
}
