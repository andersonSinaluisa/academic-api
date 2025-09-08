package com.andersonsinaluisa.academicapi.shared.infrastructure.security;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.connection.ConnectionFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
public class TenantSchemaWebFilter implements WebFilter {
    private final WebClient webClient;
    private final ConnectionFactory connectionFactory;

    public TenantSchemaWebFilter(WebClient.Builder webClientBuilder, ConnectionFactory connectionFactory) {
        this.webClient = webClientBuilder.baseUrl("http://gestor-tenants/api").build();
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String tenantId = exchange.getRequest().getHeaders().getFirst("X-Tenant-ID");

        if (tenantId == null || tenantId.isBlank()) {
            return chain.filter(exchange);
        }

        return webClient.get()
                .uri("/tenants/{tenantId}", tenantId)
                .retrieve()
                .bodyToMono(TenantResponse.class)
                .flatMap(tenant -> {
                    String schemaName = tenant.getSchemaName();
                    if (schemaName == null || !schemaName.matches("^[a-zA-Z0-9_]+$")) {
                        return chain.filter(exchange);
                    }

                    // Cambiar search_path de manera reactiva
                    return ConnectionFactoryUtils.getConnection(connectionFactory)
                            .flatMap(conn ->
                                    Mono.from(conn.createStatement("SET search_path TO \"" + schemaName + "\", public").execute())
                                            .then(Mono.defer(() -> chain.filter(exchange)))
                                            .doFinally(signal -> {
                                                // Restaurar search_path al final
                                                conn.createStatement("SET search_path TO public").execute();
                                            })
                            );
                })
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return chain.filter(exchange);
                });
    }

    static class TenantResponse {
        private String schemaName;
        public String getSchemaName() { return schemaName; }
        public void setSchemaName(String schemaName) { this.schemaName = schemaName; }
    }
}
