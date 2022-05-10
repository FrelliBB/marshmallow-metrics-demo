package com.marshmallow.demo.metrics.programmatic;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SomeServiceWithAQueueTest {

    @Test
    void shouldIncrementCounter() {
        final var meterRegistry = new SimpleMeterRegistry();
        final var service = new SomeServiceWithAQueue(meterRegistry);

        service.publishToQueue();

        final var counter = meterRegistry.get("marshmallow.queue.publishes").counter();
        assertThat(counter.count()).isEqualTo(1);

    }

}