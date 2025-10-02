package com.andersonsinaluisa.academicapi.academic.application.usecases.teacherassignment;

import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.domain.repository.TeacherAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindAllTeacherAssignmentUseCase {
    private final TeacherAssignmentRepository repository;

    public Flux<TeacherAssignmentOutputDto> execute(Pageable pageable){

    }
}
