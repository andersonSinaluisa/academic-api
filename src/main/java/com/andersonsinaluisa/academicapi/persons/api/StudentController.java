package com.andersonsinaluisa.academicapi.persons.api;

import com.andersonsinaluisa.academicapi.persons.application.dtos.StudentInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.StudentOutputDto;
import com.andersonsinaluisa.academicapi.persons.application.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.*;
import com.andersonsinaluisa.academicapi.persons.application.usecases.student.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private CreateStudentUseCase createStudentUseCase;
    @Autowired
    private ListStudentByCourseIdUseCase listStudentByCourseIdUseCase;
    @Autowired
    private GetByIdStudentUseCase getByIdStudentUseCase;
    @Autowired
    private UpdateStudentUseCase updateStudentUseCase;
    @Autowired
    private DeleteStudentUseCase deleteStudentUseCase;
    @PostMapping
    public Mono<StudentOutputDto> create(@RequestBody StudentInputDto dto){
        return createStudentUseCase.execute(
                StudentMapper.fromDtoToDomain(dto)
                )
                .map(StudentMapper::fromDomainToDto);
    }

    @GetMapping
    public Mono<Page<StudentOutputDto>> list(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("uuidParallel") String parallel
    ) {
        Pageable pageable = Pageable.ofSize(limit).withPage(page);

        return listStudentByCourseIdUseCase.execute(pageable, parallel)
                .map(p -> p.map(StudentMapper::fromDomainToDto));
    }

    @GetMapping("{id}")
    public Mono<StudentOutputDto> get(
            @PathVariable("id") Long id
    ){
        return getByIdStudentUseCase.execute(id).map(StudentMapper::fromDomainToDto);
    }


    @PatchMapping("{id}")
    public Mono<StudentOutputDto> update(@PathVariable("id") Long id,
                                         @RequestBody StudentInputDto studentInputDto){
        return updateStudentUseCase.execute(id,StudentMapper.fromDtoToDomain(studentInputDto))
                .map(StudentMapper::fromDomainToDto);
    }


    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable("id") Long id
    ){
        return deleteStudentUseCase.execute(id);
    }

}
