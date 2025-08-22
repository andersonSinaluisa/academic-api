package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.TeacherAssignmentMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SearchTeacherAssignmentsUseCase {

    private final TeacherAssignmentRepository repository;

    public Flux<TeacherAssignmentOutputDto> execute(Long teacherId, Long courseId) {
        return repository.findByTeacherIdAndCourseId(teacherId, courseId)
                .map(TeacherAssignmentMapper::toOutputDto);
    }
}
