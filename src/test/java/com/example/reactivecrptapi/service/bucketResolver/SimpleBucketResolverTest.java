package com.example.reactivecrptapi.service.bucketResolver;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketState;
import io.github.bucket4j.ConsumptionProbe;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

class SimpleBucketResolverTest {

    @Test
    void resolveBucket_returnsExpectedBucket() {
        long capacity = 1;
        Duration duration = Duration.ofHours(1);
        SimpleBucketResolver bucketResolver = new SimpleBucketResolver(capacity, duration);
        Bandwidth expectedLimit = Bandwidth.builder()
                .capacity(capacity)
                .refillIntervally(capacity, duration)
                .build();
        StepVerifier.create(bucketResolver.resolveBucket(null))
                .assertNext(bucket -> assertBucketHasExpectedLimit(bucket, expectedLimit))
                .verifyComplete();
    }

    @SuppressWarnings({"unchecked", "DataFlowIssue"})
    void assertBucketHasExpectedLimit(Bucket bucket, Bandwidth expectedLimit) {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        assertThat(probe.isConsumed()).isTrue();
        assertThat(probe.getRemainingTokens()).isEqualTo(expectedLimit.getCapacity() - 1);
        AtomicReference<BucketState> atomicState =
                ((AtomicReference<BucketState>) ReflectionTestUtils.getField(bucket, "stateRef"));
        assumeThat(atomicState).isNotNull();
        Bandwidth[] limits = atomicState.get().getConfiguration().getBandwidths();
        assertThat(limits).hasSize(1);
        Bandwidth limit = limits[0];
        assertThat(limit.getRefillPeriodNanos()).isEqualTo(expectedLimit.getRefillPeriodNanos());
    }
}