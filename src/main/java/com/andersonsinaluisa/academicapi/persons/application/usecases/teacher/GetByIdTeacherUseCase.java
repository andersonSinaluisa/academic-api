package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import reactor.core.publisher.Mono;

public class GetByIdTeacherUseCase {

    private TeacherRepository teacherRepository;

    public Mono<Teacher> execute(Long Id){
        return teacherRepository.findById(Id);
    }
}
