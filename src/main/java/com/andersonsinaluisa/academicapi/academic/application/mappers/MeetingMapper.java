package com.andersonsinaluisa.academicapi.academic.application.mappers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.MeetingDto;
import com.andersonsinaluisa.academicapi.academic.domain.entities.meeting.Meeting;
import com.andersonsinaluisa.academicapi.academic.domain.valueObjects.MeetingDate;

public class MeetingMapper {
    public static Meeting fromDtoToDomain(MeetingDto dto) {
        return Meeting.builder()
                .id(dto.id)
                .courseId(dto.courseId)
                .academicYearId(dto.academicYearId)
                .meetingDate(new MeetingDate(dto.meetingDate))
                .location(dto.location)
                .meetingType(dto.meetingType)
                .createdBy(dto.createdBy)
                .build();
    }

    public static MeetingDto fromDomainToDto(Meeting meeting) {
        return MeetingDto.builder()
                .id(meeting.id)
                .courseId(meeting.courseId)
                .academicYearId(meeting.academicYearId)
                .meetingDate(meeting.meetingDate.getValue())
                .location(meeting.location)
                .meetingType(meeting.meetingType)
                .createdBy(meeting.createdBy)
                .build();
    }
}

