package com.andersonsinaluisa.academicapi.shared.infrastructure;

import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GenericR2dbcRepository <T> {

    private final DatabaseClient client;
    private final String tableName;
    private final BiFunction<Row, RowMetadata, T> mapper;

    public GenericR2dbcRepository(DatabaseClient client, String tableName,
                                  BiFunction<io.r2dbc.spi.Row, io.r2dbc.spi.RowMetadata, T> mapper) {
        this.client = client;
        this.tableName = tableName;
        this.mapper = mapper;
    }

    public Flux<T> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE deleted = 0");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" = :").append(key);
            bindParams.put(key, value);
        });

        sql.append(" LIMIT :limit OFFSET :offset");
        bindParams.put("limit", limit);
        bindParams.put("offset", offset);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            spec = spec.bind(entry.getKey(), entry.getValue());
        }

        return spec.map(mapper).all();
    }


    public Flux<T> findAllWithLikeFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE deleted = 0");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" LIKE :").append("%".concat(key).concat("%"));
            bindParams.put(key, value);
        });

        sql.append(" LIMIT :limit OFFSET :offset");
        bindParams.put("limit", limit);
        bindParams.put("offset", offset);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            spec = spec.bind(entry.getKey(), entry.getValue());
        }

        return spec.map(mapper).all();
    }

    public Mono<Long> countWithLikeFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE deleted = 0");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" LIKE :").append("%".concat(key).concat("%"));
            bindParams.put(key, value);
        });

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            spec = spec.bind(entry.getKey(), entry.getValue());
        }

        return spec.map((row, meta) -> row.get(0, Long.class)).one();
    }
    public Mono<Long> countWithFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE deleted = 0");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" = :").append(key);
            bindParams.put(key, value);
        });

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            spec = spec.bind(entry.getKey(), entry.getValue());
        }

        return spec.map((row, meta) -> row.get(0, Long.class)).one();
    }
}
