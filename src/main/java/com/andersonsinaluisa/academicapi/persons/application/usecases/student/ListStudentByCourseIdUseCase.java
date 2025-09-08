package com.andersonsinaluisa.academicapi.persons.application.usecases.student;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ListStudentByCourseIdUseCase {

    @Autowired
    private StudentRepository studentRepository;

    public Mono<Page<Student>> execute(Pageable pageable, String uidParallel){
        FilterCriteria filtros = new FilterCriteria();
        filtros.add("uuidCurrentParallel",uidParallel);
        return studentRepository.findAll(pageable, filtros);
    }
}
