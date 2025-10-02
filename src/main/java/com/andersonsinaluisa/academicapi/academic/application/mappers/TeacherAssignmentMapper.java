package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.CourseSubject;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentInputManyDto;
import com.andersonsinaluisa.academicapi.academic.application.dtos.TeacherAssignmentOutputDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.teacher.TeacherAssignment;

import java.util.ArrayList;
import java.util.List;

public class TeacherAssignmentMapper {
    public static TeacherAssignment toDomain(TeacherAssignmentInputDto dto) {
        return TeacherAssignment.builder()
                .teacherId(dto.teacherId)
                .courseId(dto.courseId)
                .subjectId(dto.subjectId)
                .schoolYearId(dto.schoolYearId)
                .build();
    }

    public static TeacherAssignmentOutputDto toOutputDto(TeacherAssignment assignment) {
        return TeacherAssignmentOutputDto.builder()
                .id(assignment.id)
                .teacherId(assignment.teacherId)
                .courseId(assignment.courseId)
                .subjectId(assignment.subjectId)
                .schoolYearId(assignment.schoolYearId)
                .createdAt(assignment.createdAt)
                .build();
    }

    public static List<TeacherAssignment> fromManyToDomain(TeacherAssignmentInputManyDto dto){
        var teacherId = dto.teacherId;
        List<TeacherAssignment> list = new ArrayList<>();
        for (CourseSubject data: dto.listAssignment){
            list.add(
                    TeacherAssignment.builder()
                            .teacherId(teacherId)
                            .courseId(data.courseId())
                            .subjectId(data.subjectId())
                            .schoolYearId(data.schoolYearId())
                            .build()
            );
        }
        return  list;
    }
}
