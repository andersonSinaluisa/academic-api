package com.andersonsinaluisa.academicapi.academic.domain.valueObjects;

public class Score {
    private final Double value;

    public Score(Double value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Score must be positive");
        }
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}

