package com.example.reactivecrptapi.config;

import com.example.reactivecrptapi.handler.CrptHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    private final CrptHandler handler;

    public RouterConfig(CrptHandler handler) {
        this.handler = handler;
    }

    @Bean
    public RouterFunction<ServerResponse> postDocumentRouterFunction() {
        return RouterFunctions.route()
                .POST("/api/v3/lk/documents/create", handler::postDocument)
                .build();
    }
}
