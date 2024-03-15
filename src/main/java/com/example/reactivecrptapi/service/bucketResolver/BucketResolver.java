package com.example.reactivecrptapi.service.bucketResolver;

import io.github.bucket4j.Bucket;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * A type that maps a {@link ServerWebExchange} to a {@link Bucket} of tokens
 */
public interface BucketResolver {
    /**
     * Maps a {@code ServerWebExchange} to a {@code Bucket} of tokens
     *
     * @param exchange an exchange that should be mapped to a
     *                 corresponding {@code Bucket}
     * @return a {@code Mono} of a {@code Bucket} matching the exchange
     */
    Mono<Bucket> resolveBucket(ServerWebExchange exchange);
}
