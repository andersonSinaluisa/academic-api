package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.EnrollmentDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.enrollment.Enrollment;

public class EnrollmentMapper {
    public static Enrollment fromDtoToDomain(EnrollmentDto dto) {
        return Enrollment.builder()
                .id(dto.id)
                .studentId(dto.studentId)
                .courseId(dto.courseId)
                .academicYearId(dto.academicYearId)
                .enrollmentDate(dto.enrollmentDate)
                .status(dto.status)
                .build();
    }

    public static EnrollmentDto fromDomainToDto(Enrollment enrollment) {
        return EnrollmentDto.builder()
                .id(enrollment.id)
                .studentId(enrollment.studentId)
                .courseId(enrollment.courseId)
                .academicYearId(enrollment.academicYearId)
                .enrollmentDate(enrollment.enrollmentDate)
                .status(enrollment.status)
                .build();
    }
}

