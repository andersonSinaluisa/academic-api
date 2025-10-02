package com.andersonsinaluisa.academicapi.academic.application.dtos;

public record CourseSubject(
        Long courseId,
        Long subjectId,
        Long schoolYearId
) {
}
