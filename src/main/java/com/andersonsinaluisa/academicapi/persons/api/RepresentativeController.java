package com.andersonsinaluisa.academicapi.persons.api;


import com.andersonsinaluisa.academicapi.persons.application.dtos.RepresentativeInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.RepresentativeOutputDto;
import com.andersonsinaluisa.academicapi.persons.application.mappers.RepresentativeMapper;
import com.andersonsinaluisa.academicapi.persons.application.mappers.StudentMapper;
import com.andersonsinaluisa.academicapi.persons.application.usecases.representative.*;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/representative")
public class RepresentativeController {

    @Autowired
    private CreateRepresentativeUseCase createRepresentativeUseCase;
    @Autowired
    private ListRepresentativeUseCase listRepresentativeUseCase;
    @Autowired
    private GetByIdRepresentativeUseCase getByIdRepresentativeUseCase;
    @Autowired
    private UpdateRepresentativeUseCase updateRepresentativeUseCase;
    @Autowired
    private DeleteRepresentativeUseCase deleteRepresentativeUseCase;
    @PostMapping
    public Mono<RepresentativeOutputDto> create(
            @RequestBody RepresentativeInputDto representativeInputDto){
        return createRepresentativeUseCase
                .execute(RepresentativeMapper.fromDtoToDomain(representativeInputDto))
                .map(RepresentativeMapper::fromDomainToDto);
    }

    @GetMapping
    public Mono<PageResult<RepresentativeOutputDto>> list(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        Pageable pageable = Pageable.ofSize(limit).withPage(page);
        return listRepresentativeUseCase.execute(pageable)
                .map(result -> new PageResult<>(
                        result.content().stream()
                                .map(RepresentativeMapper::fromDomainToDto)
                                .toList(),
                        result.total(),
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        result.totalPage()

                ));
    }

    @GetMapping("{id}")
    public Mono<RepresentativeOutputDto> get(@PathVariable Long id){
        return getByIdRepresentativeUseCase.execute(id)
                .map(RepresentativeMapper::fromDomainToDto);
    }

    @PatchMapping("{id}")
    public Mono<RepresentativeOutputDto> update(@PathVariable Long id,
                                                @RequestBody RepresentativeInputDto representativeInputDto){
        Representative representative = RepresentativeMapper.fromDtoToDomain(representativeInputDto);
        representative.id = id;
        return updateRepresentativeUseCase.execute(representative)
                .map(RepresentativeMapper::fromDomainToDto);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable Long id){
        return deleteRepresentativeUseCase.execute(id);
    }
}
