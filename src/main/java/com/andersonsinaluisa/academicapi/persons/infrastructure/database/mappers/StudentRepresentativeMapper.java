package com.andersonsinaluisa.academicapi.persons.infrastructure.database.mappers;

import com.andersonsinaluisa.academicapi.persons.domain.entities.StudentRepresentative;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.entities.StudentRepresentativeTable;

public class StudentRepresentativeMapper {

    public static StudentRepresentativeTable fromDomainToPersistence(StudentRepresentative data){
        return StudentRepresentativeTable.builder()
                .representativeId(data.getRepresentativeId())
                .studentId(data.getStudentId())
                .isMain(data.isMain())
                .relationShip(data.getRelationShip())

                .build();
    }

    public static StudentRepresentative fromPersistenceToDomain(StudentRepresentativeTable table){
        return StudentRepresentative.builder()
                .id(table.getId())
                .representativeId(table.getRepresentativeId())
                .studentId(table.getStudentId())
                .relationShip(table.getRelationShip())
                .isMain(table.isMain())
                .build()
                ;
    }
}
