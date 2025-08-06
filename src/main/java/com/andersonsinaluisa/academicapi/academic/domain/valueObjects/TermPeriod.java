package com.andersonsinaluisa.academicapi.academic.domain.valueObjects;

import java.time.LocalDate;

public class TermPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public TermPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Invalid term period");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

