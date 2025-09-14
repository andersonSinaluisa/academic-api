package com.andersonsinaluisa.academicapi.persons.infrastructure;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Student;
import com.andersonsinaluisa.academicapi.persons.domain.entities.Teacher;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers.StudentTableMapper;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom.StudentCustomRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.StudentPgRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import com.andersonsinaluisa.academicapi.shared.domain.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private StudentPgRepository studentPgRepository;
    @Autowired
    private StudentCustomRepository studentCustomRepository;
    @Override
    public Flux<Student> getByCourse(String courseId) {
        return studentPgRepository.findAllByUuidCourse(courseId)
                .map(StudentTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Flux<Student> getByParallel(String parallelId) {
        return studentPgRepository.findAllByUuidParallel(parallelId)
                .map(StudentTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Flux<Student> getBySchoolYear(String schoolYearId) {
        return studentPgRepository.findAllByUuidSchoolYear(schoolYearId)
                .map(StudentTableMapper::fromPersistenceToDomain);
    }

    @Override
    public Mono<PageResult<Student>> findAll(Pageable pageable, FilterCriteria filters) {
        int pageNumber = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;
        Mono<List<Student>> content = studentCustomRepository
                .findAllWithFilters(filters,pageSize, offset)
                .map(StudentTableMapper::fromPersistenceToDomain)
                .collectList();

        Mono<Long> count = studentCustomRepository.countWithFilters(filters);

        return Mono.zip(content, count)
                .map(tuple -> {
                    List<Student> teachers = tuple.getT1();
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
    public Mono<Student> create(Student student) {
        return  studentPgRepository
                .save(StudentTableMapper.fromDomainToPersistence(student))
                .map(StudentTableMapper::fromPersistenceToDomain)
                ;
    }

    @Override
    public Mono<Student> update(Long id,Student student) {
        student.id = id;
        return studentPgRepository
                .save(StudentTableMapper.fromDomainToPersistence(student))
                .map(StudentTableMapper::fromPersistenceToDomain)
                ;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return studentPgRepository.deleteById(id);
    }

    @Override
    public Mono<Student> getById(Long id) {
        return studentPgRepository.findById(id)
                .map(StudentTableMapper::fromPersistenceToDomain);
    }
}
