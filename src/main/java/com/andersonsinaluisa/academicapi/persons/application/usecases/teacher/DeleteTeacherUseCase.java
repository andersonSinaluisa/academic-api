package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class DeleteTeacherUseCase {
    private TeacherRepository teacherRepository;
    public Mono<Void> execute(Long Id){
        return teacherRepository.remove(Id);
    }
}
