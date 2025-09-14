package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.application.exception.TeacherAlreadyExistsException;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateTeacherUseCase {

    @Autowired
    private TeacherRepository teacherRepository;

    public Mono<Teacher> execute(Teacher teacher) {
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.add("identification", teacher.identification.getValue());

        // Buscar si ya existe
        return teacherRepository.findAll(Pageable.ofSize(100), filterCriteria)
                .flatMap(pageResult -> {
                    if (!pageResult.content().isEmpty()) {
                        // Si ya existe -> error
                        return Mono.error(new TeacherAlreadyExistsException(
                                "Ya existe un profesor con la misma identificación"));
                    }
                    // Si no existe -> crear
                    return teacherRepository.create(teacher);
                });
    }
}