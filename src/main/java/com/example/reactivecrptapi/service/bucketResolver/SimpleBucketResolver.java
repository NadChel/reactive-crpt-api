package com.example.reactivecrptapi.service.bucketResolver;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * A simplistic {@link BucketResolver} implementation that provides one shared {@code Bucket}
 * for all exchanges
 */
@Component
public class SimpleBucketResolver implements BucketResolver {
    private final Bucket sharedBucket;

    public SimpleBucketResolver(@Value("${crpt.rate-limiter.capacity}") long capacity,
                                @Value("${crpt.rate-limiter.duration}") Duration duration) {
        Bandwidth globalLimit = Bandwidth.builder()
                .capacity(capacity)
                .refillIntervally(capacity, duration)
                .build();
        this.sharedBucket = Bucket.builder()
                .addLimit(globalLimit)
                .build();
    }

    /**
     * Returns a shared {@code Bucket} of tokens configured according to
     * application properties
     *
     * @param exchange a nullable exchange that will be ignored
     */
    @Override
    public Mono<Bucket> resolveBucket(@Nullable ServerWebExchange exchange) {
        return Mono.just(sharedBucket);
    }
}
