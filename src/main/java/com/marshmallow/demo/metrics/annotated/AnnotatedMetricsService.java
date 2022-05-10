package com.marshmallow.demo.metrics.annotated;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Shows metrics defined via annotations. This requires the addition of spring-boot-starter-aop
 * and configuration of the aspects (see {@link MicrometerAspectConfiguration}).
 */
@Service
@RequiredArgsConstructor
public class AnnotatedMetricsService {

    private static final Random r = new Random();

    @Timed(value = "marshmallow.annotated.dosomething", description = "Timer for the doSomething() method")
    public void doSomething() {
        sendApiRequest(); // this self-invocation bypasses Spring proxies and the @Counted aspect will not be invoked!
    }

    @Counted(value = "marshmallow.annotated.sendrequest", description = "Counter for the sendApiRequest() method")
    public void sendApiRequest() {
        randomSleep();
    }

    @SneakyThrows
    private void randomSleep() {
        final var sleep = r.nextDouble() * 1000.0;
        Thread.sleep((long) sleep);
    }
}
