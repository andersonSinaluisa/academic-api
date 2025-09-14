package com.andersonsinaluisa.academicapi.persons.application.usecases.teacher;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ListTeacherUseCase {
    @Autowired
    private TeacherRepository teacherRepository;
    public Mono<PageResult<Teacher>> execute(Pageable pageable,
                                             String firstName, String lastName, String identification, String gender){

        FilterCriteria filters = new FilterCriteria();
        if (firstName!=null && !firstName.isEmpty()){
            filters.add("first_name",firstName);
        }

        if(lastName!=null && !lastName.isEmpty()){
            filters.add("last_name",lastName);
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
