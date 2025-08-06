package com.andersonsinaluisa.academicapi.persons.domain.entities;



import lombok.*;
import lombok.experimental.SuperBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@SuperBuilder
@Getter
@Setter
public class Student extends Person{

    public String uuidCurrentParallel;
    public String uuidCurrentSchoolYear;
    public String uuidCurrentSection;
    public List<StudentRepresentative> studentRepresentatives;
    public Student studentRepresentatives(List<StudentRepresentative> studentRepresentatives){
        if(studentRepresentatives.size()>2)
        {
            throw new IllegalArgumentException("No se puede tener mas de un representante");
        }
        if (studentRepresentatives.stream().filter(
                e -> e.isMain()
            ).count()>1){
            throw new IllegalArgumentException("Solo un representante puede ser principal");
        }

        if( studentRepresentatives.stream().noneMatch(e -> e.isMain())){
            throw  new IllegalArgumentException("Al menos un representante debe ser principal");
        }
        return this;
    }
}
