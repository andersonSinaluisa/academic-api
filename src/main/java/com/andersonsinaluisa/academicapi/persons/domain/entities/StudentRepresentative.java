package com.andersonsinaluisa.academicapi.persons.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StudentRepresentative {
    private Long id;
    private Long studentId;
    private Long representativeId;
    private Student student;
    private Representative representative;
    private String relationShip;
    private boolean isMain;


}
