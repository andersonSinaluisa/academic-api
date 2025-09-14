package com.andersonsinaluisa.academicapi.shared.infrastructure.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Simple filter that requires teacher endpoints to include the X-Teacher-Id header.
 */

public class TeacherAuthFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        if (path.startsWith("/teacher-")) {
            String teacherId = exchange.getRequest().getHeaders().getFirst("X-Teacher-Id");
            if (teacherId == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }
}
