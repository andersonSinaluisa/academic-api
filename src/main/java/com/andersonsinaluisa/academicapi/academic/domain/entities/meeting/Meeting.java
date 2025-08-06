package com.andersonsinaluisa.academicapi.academic.domain.entities.meeting;

import com.andersonsinaluisa.academicapi.academic.domain.valueObjects.MeetingDate;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    public Long id;
    public String courseId;
    public String academicYearId;
    public MeetingDate meetingDate;
    public String location;
    public String meetingType;
    public Long createdBy;
}

