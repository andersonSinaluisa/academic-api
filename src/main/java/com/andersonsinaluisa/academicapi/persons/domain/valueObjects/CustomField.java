package com.andersonsinaluisa.academicapi.persons.domain.valueObjects;

import java.util.Objects;

public class CustomField {

    private final String key;
    private final String label;
    private final String value;

    public CustomField(String label, String value) {
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Label cannot be null or blank");
        }
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Value cannot be null or blank");
        }
        this.label = label.trim();
        this.value = value.trim();
        this.key = generateKeyFromLabel(this.label);
    }

    private String generateKeyFromLabel(String label) {
        return label.toLowerCase().replaceAll("\\s+", "-");
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    // Override equals and hashCode for value object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomField)) return false;
        CustomField that = (CustomField) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(label, that.label) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, label, value);
    }

    @Override
    public String toString() {
        return "CustomField{" +
                "key='" + key + '\'' +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
