package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ListTeacherUseCase {
    private TeacherRepository teacherRepository;
    public Mono<Page<Teacher>> execute(Pageable pageable,
    String firstName, String lastName, String identification,String gender){

        FilterCriteria filters = new FilterCriteria();
        if (firstName!=null && !filters.isEmpty()){
            filters.add("firstName",firstName);
        }

        if(lastName!=null && !lastName.isEmpty()){
            filters.add("lastName",lastName);
        }

        if(identification!=null && !identification.isEmpty()){
            filters.add("identification",identification);
        }

        if(gender!=null && !gender.isEmpty()){
            filters.add("gender",gender);
        }

        return  teacherRepository.findAll(pageable,filters);
    }
}
