package com.andersonsinaluisa.academicapi.persons.api;


import com.andersonsinaluisa.academicapi.persons.application.dtos.RepresentativeInputDto;
import com.andersonsinaluisa.academicapi.persons.application.dtos.RepresentativeOutputDto;
import com.andersonsinaluisa.academicapi.persons.application.mappers.RepresentativeMapper;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/representative")
public class RepresentativeController {

    private RepresentativeRepository representativeRepository;
    @PostMapping
    public Mono<RepresentativeOutputDto> create(
            @RequestBody RepresentativeInputDto representativeInputDto){
        return representativeRepository
                .create(
                        RepresentativeMapper
                                .fromDtoToDomain(representativeInputDto))
                .map(RepresentativeMapper::fromDomainToDto);
    }
}
