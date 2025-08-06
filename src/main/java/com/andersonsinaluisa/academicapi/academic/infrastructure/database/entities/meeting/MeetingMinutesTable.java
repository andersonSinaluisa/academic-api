package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.meeting;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="meeting_minutes")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MeetingMinutesTable {
    @Id
    public Long id;
    public Long meetingId;
    public String summary;
    public String decisions;
    public String nextSteps;
}

