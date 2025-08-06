package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.attendance;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="attendance")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AttendanceTable {
    @Id
    public Long id;
    public String subjectId;
    public Long studentId;
    public LocalDate date;
    public String status;
    public String observation;
}

