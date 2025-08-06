package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.meeting;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="meeting_attendance")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MeetingAttendanceTable {
    @Id
    public Long id;
    public Long meetingId;
    public Long personId;
    public String role;
    public String status;
}

