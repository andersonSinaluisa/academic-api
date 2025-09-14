package com.andersonsinaluisa.academicapi.persons.infrastructure;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.repository.RepresentativeRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers.RepresentativeTableMapper;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.RepresentativePgRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom.RepresentativeCustomRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Repository
public class RepresentativeRepositoryImpl implements RepresentativeRepository {

    @Autowired
    private  RepresentativePgRepository representativePgRepository;
    @Autowired
    private  RepresentativeCustomRepository representativeCustomRepository;

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
    public Mono<PageResult<Representative>> all(Pageable pageable,
                                                FilterCriteria filterCriteria) {

        int pageNumber = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        Mono<List<Representative>> content = representativeCustomRepository
                .findAllWithFilters(filterCriteria,pageSize, offset)
                .map(RepresentativeTableMapper::fromPersistenceToDomain)
                .collectList();

        Mono<Long> count = representativeCustomRepository.countWithFilters(filterCriteria);

        return Mono.zip(content, count)
                .map(tuple -> {
                    List<Representative> teachers = tuple.getT1();
                    Long totalElements = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) totalElements / pageSize);

                    return new PageResult<>(
                            teachers,
                            totalElements,
                            pageNumber,
                            pageSize,
                            totalPages
                    );
                });
    }
}
