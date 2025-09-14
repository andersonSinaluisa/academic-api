package com.andersonsinaluisa.academicapi.persons.infrastructure;

import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import com.andersonsinaluisa.academicapi.persons.domain.repository.StudentRepresentiveRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers.StudentRepresentativeMapper;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.StudenRepresentativePgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public class StudentRepresentativeRepositoryImpl implements StudentRepresentiveRepository {

    @Autowired
    private  StudenRepresentativePgRepository representativePgRepository;

    @Override
    public Mono<Void> unAssign(Long studentId, Long representativeId) {
        return this.representativePgRepository.unasign(studentId,representativeId);
    }

    @Override
    public Mono<StudentRepresentative> assign(
            StudentRepresentative studentRepresentative) {
        return representativePgRepository.save(StudentRepresentativeMapper.fromDomainToPersistence(studentRepresentative))
                .map(StudentRepresentativeMapper::fromPersistenceToDomain)
                ;
    }
}
