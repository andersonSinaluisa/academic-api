package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.meeting;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name="meeting")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MeetingTable {
    @Id
    public Long id;
    public String courseId;
    public String academicYearId;
    public LocalDate meetingDate;
    public String location;
    public String meetingType;
    public Long createdBy;
}

