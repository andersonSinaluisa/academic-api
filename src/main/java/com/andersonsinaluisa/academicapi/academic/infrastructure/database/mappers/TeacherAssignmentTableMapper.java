package com.andersonsinaluisa.academicapi.academic.infrastructure.database.mappers;

import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.enrollment.TeacherAssignmentTable;

public final class TeacherAssignmentTableMapper {

    private TeacherAssignmentTableMapper() {
    }

    public static TeacherAssignment fromPersistenceToDomain(TeacherAssignmentTable table) {
        if (table == null) {
            return null;
        }
        return TeacherAssignment.builder()
                .id(table.id)
                .teacherId(table.teacherId)
                .courseId(parseLong(table.courseId))
                .subjectId(parseLong(table.subjectId))
                .schoolYearId(table.academicYearId)
                .createdAt(table.createdAt)
                .build();
    }

    public static TeacherAssignmentTable fromDomainToPersistence(TeacherAssignment assignment) {
        if (assignment == null) {
            return null;
        }
        return TeacherAssignmentTable.builder()
                .id(assignment.id)
                .teacherId(assignment.teacherId)
                .courseId(toString(assignment.courseId))
                .subjectId(toString(assignment.subjectId))
                .academicYearId(assignment.schoolYearId)
                .createdAt(assignment.createdAt)
                .build();
    }

    private static String toString(Long value) {
        return value != null ? value.toString() : null;
    }

    private static Long parseLong(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
