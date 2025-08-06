package com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.meeting;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="meeting_attachment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MeetingAttachmentTable {
    @Id
    public Long id;
    public Long meetingId;
    public String fileUrl;
    public String description;
}

