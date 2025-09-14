package com.andersonsinaluisa.academicapi.shared.infrastructure;

import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import io.r2dbc.spi.Parameter;
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

    /**
     * Helper para bindeo seguro (usado solo en LIKE).
     */
    private DatabaseClient.GenericExecuteSpec bindParams(DatabaseClient.GenericExecuteSpec spec,
                                                         Map<String, Object> bindParams) {
        for (Map.Entry<String, Object> entry : bindParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) {
                log.debug("Binding NULL param [{}]", key);
                spec = spec.bindNull(key, Object.class);
            } else if (value instanceof Parameter) {
                Object realValue = ((Parameter) value).getValue();
                if (realValue == null) {
                    spec = spec.bindNull(key, Object.class);
                } else {
                    spec = spec.bind(key, realValue);
                }
            } else {
                log.debug("Binding param [{}] = {}", key, value);
                spec = spec.bind(key, value);
            }
        }
        return spec;
    }

    /**
     * 🚀 Concatenación directa → evita InParameter.
     */
    public Flux<T> findAllWithFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ")
                .append(tableName)
                .append(" WHERE deleted = false");

        filters.asMap().forEach((key, value) -> {
            if (value == null) {
                sql.append(" AND ").append(key).append(" IS NULL");
            } else {
                sql.append(" AND ").append(key)
                        .append(" = '").append(value.replace("'", "''")).append("'");
            }
        });

        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        log.info("Executing SQL (direct concat): {}", sql);

        return client.sql(sql.toString()).map(mapper).all();
    }

    public Mono<Long> countWithFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*)::bigint as total FROM ")
                .append(tableName)
                .append(" WHERE deleted = false");

        filters.asMap().forEach((key, value) -> {
            if (value == null) {
                sql.append(" AND ").append(key).append(" IS NULL");
            } else {
                sql.append(" AND ").append(key)
                        .append(" = '").append(value.replace("'", "''")).append("'");
            }
        });

        log.info("Executing SQL (direct concat): {}", sql);

        return client.sql(sql.toString())
                .map((row, meta) -> {
                    Object val = row.get("total");
                    return (val instanceof Number) ? ((Number) val).longValue() : 0L;
                })
                .one();
    }

    /**
     * Métodos con LIKE → se mantienen con bind seguro.
     */
    public Flux<T> findAllWithLikeFilters(FilterCriteria filters, int limit, int offset) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ")
                .append(tableName)
                .append(" WHERE deleted = false");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" LIKE :").append(key);
            bindParams.put(key, value == null ? null : "%" + value + "%");
        });

        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        log.info("Executing SQL: {} with params {}", sql, bindParams);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        spec = bindParams(spec, bindParams);

        return spec.map(mapper).all();
    }

    public Mono<Long> countWithLikeFilters(FilterCriteria filters) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*)::bigint as total FROM ")
                .append(tableName)
                .append(" WHERE deleted = false");

        Map<String, Object> bindParams = new HashMap<>();
        filters.asMap().forEach((key, value) -> {
            sql.append(" AND ").append(key).append(" LIKE :").append(key);
            bindParams.put(key, value == null ? null : "%" + value + "%");
        });

        log.info("Executing SQL: {} with params {}", sql, bindParams);

        DatabaseClient.GenericExecuteSpec spec = client.sql(sql.toString());
        spec = bindParams(spec, bindParams);

        return spec.map((row, meta) -> {
            Object val = row.get("total");
            return (val instanceof Number) ? ((Number) val).longValue() : 0L;
        }).one();
    }
}
