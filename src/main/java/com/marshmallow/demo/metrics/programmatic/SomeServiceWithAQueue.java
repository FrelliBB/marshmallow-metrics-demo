package com.marshmallow.demo.metrics.programmatic;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Shows programmatically defined metrics using a simple queue.
 */
@Slf4j
@Service
public class SomeServiceWithAQueue {

    private final Random r = new Random();

    private final Queue<Integer> queue = new ConcurrentLinkedQueue<>();

    private final Counter counter;
    private final Timer timer;

    public SomeServiceWithAQueue(MeterRegistry meterRegistry) {
        Gauge.builder("marshmallow.queue.size", queue::size).register(meterRegistry);
        this.counter = Counter.builder("marshmallow.queue.publishes").register(meterRegistry);
        this.timer = Timer.builder("marshmallow.queue.latency").register(meterRegistry);
    }

    @Scheduled(fixedDelay = 500)
    void publishToQueue() {
        log.info("Publishing message . . .");
        queue.add(0);
        counter.increment();
    }

    @Scheduled(fixedDelay = 1000)
    void consumeFromQueue() {
        timer.record(() -> {
            log.info("Polling queue . . .");
            if (r.nextDouble() < 0.5) {
                queue.poll();
                queue.poll();
            }
            queue.poll();
        });
    }

}
