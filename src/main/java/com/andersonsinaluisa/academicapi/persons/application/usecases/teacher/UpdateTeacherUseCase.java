package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UpdateTeacherUseCase {
    @Autowired
    private TeacherRepository  teacherRepository;
    public Mono<Teacher> execute(Teacher teacher){
       return teacherRepository.findById(teacher.id).flatMap(
              r -> teacherRepository.update(teacher)
        );

    }
}
