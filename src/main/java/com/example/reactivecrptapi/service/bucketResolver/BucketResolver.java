package com.example.reactivecrptapi.service.bucketResolver;

import io.github.bucket4j.Bucket;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * A type that maps a {@link ServerWebExchange} to a {@link Bucket} of tokens
 */
public interface BucketResolver {
    Mono<Bucket> resolveBucket(ServerWebExchange exchange);
}
