package com.andersonsinaluisa.academicapi.shared.domain;

import java.util.List;

public record PageResult<T>(
        List<T> content,
        long total,
        int page,
        int size,
        long totalPage
) {}