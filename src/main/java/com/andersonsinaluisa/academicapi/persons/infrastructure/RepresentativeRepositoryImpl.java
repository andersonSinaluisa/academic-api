package com.andersonsinaluisa.academicapi.persons.infrastructure;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers.RepresentativeTableMapper;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.RepresentativePgRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom.RepresentativeCustomRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public class RepresentativeRepositoryImpl implements RepresentativeRepository {


    private final RepresentativePgRepository representativePgRepository;
    private final RepresentativeCustomRepository representativeCustomRepository;

    public RepresentativeRepositoryImpl (RepresentativePgRepository representativePgRepository,
    RepresentativeCustomRepository representativeCustomRepository){
        this.representativePgRepository = representativePgRepository;
        this.representativeCustomRepository = representativeCustomRepository;
    }

    @Override
    public Mono<Representative> create(Representative representative) {
        return representativePgRepository
                .save(RepresentativeTableMapper.fromDomainToPersistence(representative))
                .map(RepresentativeTableMapper::fromPersistenceToDomain)
                ;
    }

    @Override
    public Mono<Representative> update(Representative representative) {
        return representativePgRepository.save(RepresentativeTableMapper.fromDomainToPersistence(representative))
                .map(RepresentativeTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return representativePgRepository.deleteById(id);
    }

    @Override
    public Flux<Representative> getByStudent(Long studentId) {
        return representativePgRepository
                .findByStudentId(studentId)
                .map(RepresentativeTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<Representative> getById(Long id) {
        return representativePgRepository.findById(id)
                .map(RepresentativeTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<Page<Representative>> all(Pageable pageable,
                                          FilterCriteria filterCriteria) {
        int pageSize = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        Mono<List<Representative>> content = representativeCustomRepository
                .findAllWithFilters(filterCriteria,pageSize, offset)
                .map(RepresentativeTableMapper::fromPersistenceToDomain)
                .collectList();

        Mono<Long> count = representativeCustomRepository.countWithFilters(filterCriteria);

        return Mono.zip(content, count)
                .map(tuple ->
                        new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));

    }
}
