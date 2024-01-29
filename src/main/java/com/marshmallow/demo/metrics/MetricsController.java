package com.marshmallow.demo.metrics;

import com.marshmallow.demo.metrics.annotated.AnnotatedMetricsService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricsController {
    private final AnnotatedMetricsService annotatedMetricsService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        annotatedMetricsService.doSomething();
        return "hello " + name;
    }

    private final MeterRegistry meterRegistry;

    @GetMapping("/hello/{name}/tagged")
    public String helloTagged(@PathVariable("name") String name) {
        meterRegistry.counter("marshmallow.hello.tagged",
            "name", name,
            "other_tag", "other_value"
        ).increment();
        annotatedMetricsService.doSomething();
        return "hello " + name;
    }

}
