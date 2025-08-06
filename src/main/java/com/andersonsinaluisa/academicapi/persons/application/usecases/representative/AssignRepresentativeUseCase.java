package com.andersonsinaluisa.academicapi.persons.application.usecases.representative;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AssignRepresentativeUseCase {

    private StudentRepresentiveRepository representativeRepository;

    public Mono<StudentRepresentative> execute(
            Representative representative,
            Student student,
            String relationShip,
            boolean isMain
    ){

         return representativeRepository.assign(StudentRepresentative.builder()
                         .representativeId(representative.id)
                         .relationShip(relationShip)
                         .isMain(isMain)
                         .studentId(student.id)
                         .representative(representative)
                         .student(student)
                 .build());
    }
}
