package com.andersonsinaluisa.academicapi.shared.infrastructure;

import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GenericR2dbcRepository<T> {

    private static final Logger log = LoggerFactory.getLogger(GenericR2dbcRepository.class);

    private final DatabaseClient client;
    private final String tableName;
    private final BiFunction<Row, RowMetadata, T> mapper;

    public GenericR2dbcRepository(DatabaseClient client, String tableName,
                                  BiFunction<Row, RowMetadata, T> mapper) {
        this.client = client;
        this.tableName = tableName;
        this.mapper = mapper;
    }

    private DatabaseClient.GenericExecuteSpec bindParams(DatabaseClient.GenericExecuteSpec spec,
                                                         Map<String, Object> bindParams) {
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) {
                log.debug("Binding NULL param [{}]", key);
                spec = spec.bindNull(key, String.class); // 👈 ajustar tipo según tu caso
            } else {
                log.debug("Binding param [{}] = {}", key, value);
                spec = spec.bind(key, value);
            }
        }
        return spec;
    }

    public Flux<T> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ")
                .append(tableName)
                .append(" WHERE deleted = false");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" = :").append(key);
            bindParams.put(key, value);
        });

        // 🚀 concatenar directamente
        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        log.info("Executing SQL: {} with params {}", sql, bindParams);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            if (entry.getValue() == null) {
                spec = spec.bindNull(entry.getKey(), String.class);
            } else {
                spec = spec.bind(entry.getKey(), entry.getValue());
            }
        }

        return spec.map(mapper).all();
    }

    public Flux<T> findAllWithLikeFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE deleted = false");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" LIKE :").append(key);
            bindParams.put(key, value == null ? null : "%" + value + "%");
        });

        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        log.info("Executing SQL: {} with params {}", sql, bindParams);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            if (entry.getValue() == null) {
                spec = spec.bindNull(entry.getKey(), String.class);
            } else {
                spec = spec.bind(entry.getKey(), entry.getValue());
            }
        }
        return spec.map(mapper).all();
    }

    public Mono<Long> countWithLikeFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE deleted = false");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" LIKE :").append(key);
            bindParams.put(key, value == null ? null : "%" + value + "%");
        });

        log.info("Executing SQL: {} with params {}", sql, bindParams);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        spec = bindParams(spec, bindParams);

        return spec.map((row, meta) -> row.get(0, Long.class)).one();
    }

    public Mono<Long> countWithFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE deleted = false");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" = :").append(key);
            bindParams.put(key, value);
        });

        log.info("Executing SQL: {} with params {}", sql, bindParams);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        spec = bindParams(spec, bindParams);

        return spec.map((row, meta) -> row.get(0, Long.class)).one();
    }
}
