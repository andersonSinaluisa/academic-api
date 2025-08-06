package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MeetingDto {
    public Long id;
    public String courseId;
    public String academicYearId;
    public LocalDateTime meetingDate;
    public String location;
    public String meetingType;
    public Long createdBy;
}

