package com.example.reactivecrptapi.filter;

import com.example.reactivecrptapi.service.bucketResolver.BucketResolver;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.Duration;

@Component
public class RateLimitingWebFilter implements WebFilter {
    private final BucketResolver bucketResolver;

    public RateLimitingWebFilter(BucketResolver bucketResolver) {
        this.bucketResolver = bucketResolver;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return bucketResolver.resolveBucket(exchange)
                .map(bucket -> bucket.tryConsumeAndReturnRemaining(1))
                .flatMap(probe -> probe.isConsumed() ?
                        chain.filter(exchange) :
                        write429Response(exchange, probe));
    }

    private Mono<Void> write429Response(ServerWebExchange exchange, ConsumptionProbe probe) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return writeResponseBody(response, probe);
    }

    private Mono<Void> writeResponseBody(ServerHttpResponse response, ConsumptionProbe probe) {
        Duration timeToRefill = Duration.ofNanos(probe.getNanosToWaitForRefill());
        String humanReadableTimeToRefill = makeReadable(timeToRefill);
        String message = MessageFormat.format(
                "Too many requests. Please wait {0} and make another attempt",
                humanReadableTimeToRefill
        );
        DataBuffer bodyDataBuffer = DefaultDataBufferFactory.sharedInstance
                .wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    private String makeReadable(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }
}
