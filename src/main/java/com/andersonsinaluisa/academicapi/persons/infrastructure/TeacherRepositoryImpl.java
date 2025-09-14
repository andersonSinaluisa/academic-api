package com.andersonsinaluisa.academicapi.persons.infrastructure;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.TeacherRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers.RepresentativeTableMapper;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers.TeacherTableMapper;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.TeacherPgRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom.TeacherCustomRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    private TeacherPgRepository teacherPgRepository;
    @Autowired
    private TeacherCustomRepository teacherCustomRepository;
    @Override
    public Mono<Teacher> create(Teacher teacher) {
        return teacherPgRepository.save(
                TeacherTableMapper.fromDomainToPersistence(teacher)
        ).map(TeacherTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<Teacher> update(Teacher teacher) {
        return teacherPgRepository.save(
                TeacherTableMapper.fromDomainToPersistence(teacher)
        ).map(TeacherTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<PageResult<Teacher>> findAll(Pageable pageable, FilterCriteria filters) {
        int pageNumber = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        Mono<List<Teacher>> content = teacherCustomRepository
                .findAllWithFilters(filters, pageSize, offset)
                .map(TeacherTableMapper::fromPersistenceToDomain)
                .collectList();

        Mono<Long> count = teacherCustomRepository.countWithFilters(filters);

        return Mono.zip(content, count)
                .map(tuple -> {
                    List<Teacher> teachers = tuple.getT1();
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

    @Override
    public Mono<Void> remove(Long id) {
        return teacherPgRepository.remove(id);
    }

    @Override
    public Mono<Teacher> findById(Long Id) {
        return teacherPgRepository.findById(Id).map(TeacherTableMapper::fromPersistenceToDomain);
    }
}
