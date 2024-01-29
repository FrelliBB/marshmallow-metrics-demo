A project used to demo a primer on metrics with Spring Boot, Micrometer and Prometheus.

### Demo Commands

Make some requests:

```shell
curl http://localhost:8080/hello/francesco
curl http://localhost:8080/hello/charlie
curl http://localhost:8080/hello/max/tagged
curl http://localhost:8080/hello/heather/tagged
```

Get marshmallow metrics from Prometheus endpoint:

```shell
curl http://localhost:8080/actuator/prometheus | grep marshmallow_ | grep -v "#"
```

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Metrics](https://docs.spring.io/spring-metrics/docs/current/public/prometheus)
* [Prometheus](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-export-prometheus)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)
* [Spring Metrics](https://docs.spring.io/spring-metrics/docs/current/public/prometheus)