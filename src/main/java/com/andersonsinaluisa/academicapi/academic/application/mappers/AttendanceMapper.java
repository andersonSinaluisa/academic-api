package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.attendance.Attendance;

public class AttendanceMapper {
    public static Attendance fromDtoToDomain(AttendanceDto dto) {
        return Attendance.builder()
                .id(dto.id)
                .subjectId(dto.subjectId)
                .studentId(dto.studentId)
                .date(dto.date)
                .status(dto.status)
                .observation(dto.observation)
                .build();
    }

    public static AttendanceDto fromDomainToDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.id)
                .subjectId(attendance.subjectId)
                .studentId(attendance.studentId)
                .date(attendance.date)
                .status(attendance.status)
                .observation(attendance.observation)
                .build();
    }
}

