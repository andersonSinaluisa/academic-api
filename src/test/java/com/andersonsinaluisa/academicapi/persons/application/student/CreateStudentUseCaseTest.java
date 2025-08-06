package com.andersonsinaluisa.academicapi.persons.application.student;


import com.andersonsinaluisa.academicapi.persons.application.usecases.student.CreateStudentUseCase;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class CreateStudentUseCaseTest {

    private StudentRepository studentRepository;
    private RepresentativeRepository representativeRepository;
    private StudentRepresentiveRepository studentRepresentiveRepository;
    private CreateStudentUseCase createStudentUseCase;

    @BeforeEach
    void setUp() {
        studentRepository = Mockito.mock(StudentRepository.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);
        studentRepresentiveRepository = Mockito.mock(StudentRepresentiveRepository.class);

        createStudentUseCase = new CreateStudentUseCase();
        createStudentUseCase.studentRepository = studentRepository;
        createStudentUseCase.representativeRepository = representativeRepository;
        createStudentUseCase.studentRepresentiveRepository = studentRepresentiveRepository;
    }

    @Test
    void shouldCreateStudentAndAssignRepresentatives() {
        // Arrange
        Student student = Student.builder().id(1L).build();
        Representative rep1 = Representative.builder().id(100L).build();
        Representative rep2 = Representative.builder().id(101L).build();
        List<Representative> reps = List.of(rep1, rep2);

        Mockito.when(studentRepository.create(any(Student.class)))
                .thenReturn(Mono.just(student));

        Mockito.when(studentRepresentiveRepository.assign(any(StudentRepresentative.class)))
                .thenReturn(Mono.just(StudentRepresentative.builder().build()));

        // Act
        Mono<Student> result = createStudentUseCase.execute(student);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(s -> s.id.equals(1L))
                .verifyComplete();

        // Optional: verify interactions
        Mockito.verify(studentRepository).create(student);
        Mockito.verify(studentRepresentiveRepository, Mockito.times(2)).assign(any(StudentRepresentative.class));
    }
}
