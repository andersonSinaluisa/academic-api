package com.andersonsinaluisa.academicapi.academic.domain.valueObjects;

import java.time.LocalDateTime;

public class MeetingDate {
    private final LocalDateTime value;

    public MeetingDate(LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("Meeting date cannot be null");
        }
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }
}

