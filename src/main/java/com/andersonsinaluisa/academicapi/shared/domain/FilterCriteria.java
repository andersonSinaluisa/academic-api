package com.andersonsinaluisa.academicapi.shared.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FilterCriteria {
    private final Map<String, String> filters = new HashMap<>();

    public void add(String key, String value) {
        if (key != null && value != null && !value.isBlank()) {
            filters.put(key, value);
        }
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(filters.get(key));
    }

    public boolean has(String key) {
        return filters.containsKey(key);
    }

    public boolean isEmpty() {
        return filters.isEmpty();
    }

    public Map<String, String> asMap() {
        return Map.copyOf(filters);
    }

    @Override
    public String toString() {
        return "FilterCriteria" + filters;
    }
}
