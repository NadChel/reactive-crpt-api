package com.example.reactivecrptapi.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface CrptHandler {
    Mono<ServerResponse> postDocument(ServerRequest request);
}
