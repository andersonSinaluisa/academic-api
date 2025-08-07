package com.andersonsinaluisa.academicapi.persons.api;

import com.andersonsinaluisa.academicapi.persons.application.dtos.TeacherInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.TeacherOutputDto;
import com.andersonsinaluisa.academicapi.persons.application.mappers.TeacherMapper;
import com.andersonsinaluisa.academicapi.persons.application.usecases.teacher.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private CreateTeacherUseCase createTeacherUseCase;
    private ListTeacherUseCase listTeacherUseCase;
    private GetByIdTeacherUseCase getByIdTeacherUseCase;
    private UpdateTeacherUseCase updateTeacherUseCase;
    private DeleteTeacherUseCase deleteTeacherUseCase;

    @PostMapping
    public Mono<TeacherOutputDto> create(@RequestBody TeacherInputDto dto){
        return createTeacherUseCase.execute(TeacherMapper.fromDtoToDomain(dto))
                .map(TeacherMapper::fromDomainToDto);
    }

    @GetMapping
    public Mono<Page<TeacherOutputDto>> list(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "identification", required = false) String identification,
            @RequestParam(value = "gender", required = false) String gender
    ){
        Pageable pageable = Pageable.ofSize(limit).withPage(page);
        return listTeacherUseCase.execute(pageable, firstName, lastName, identification, gender)
                .map(p -> p.map(TeacherMapper::fromDomainToDto));
    }

    @GetMapping("{id}")
    public Mono<TeacherOutputDto> get(@PathVariable Long id){
        return getByIdTeacherUseCase.execute(id)
                .map(TeacherMapper::fromDomainToDto);
    }

    @PatchMapping("{id}")
    public Mono<TeacherOutputDto> update(@PathVariable Long id, @RequestBody TeacherInputDto dto){
        var teacher = TeacherMapper.fromDtoToDomain(dto);
        teacher.id = id;
        return updateTeacherUseCase.execute(teacher)
                .map(TeacherMapper::fromDomainToDto);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable Long id){
        return deleteTeacherUseCase.execute(id);
    }
}
