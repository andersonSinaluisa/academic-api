package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UpdateTeacherUseCase {
    private TeacherRepository  teacherRepository;
    public Mono<Teacher> execute(Teacher teacher){
        return teacherRepository.update(teacher);
    }
}
