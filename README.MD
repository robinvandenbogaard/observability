# Usage

First build the runnable jars for Quarkus and Spring
```bash
mvn package
```

Then start the applications and monitoring tools
```bash
docker compose up
```

## Metrics

Open http://localhost:9000 to open the Prometheus explorer. Search for metrics, i.e.
 - jvm_memory_committed_bytes
 - jvm_memory_max_bytes

For all **Quarkus** metrics visit http://localhost:8000/q/metrics

For all **Spring** metrics visit http://localhost:8001/actuator/prometheus

The Prometheus instance has been configured to scrape both applications. You can find this configuration in [prometheus.yml](prometheus/prometheus.yml).

## Dashboard

Open http://localhost:3000 to open Grafana. Login with admin/admin.

Here you can add any dashboard you want. Not covered in this repo.
However if you import the [JVM (Micrometer)](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/) dashboard. 
You can start monitoring resource usage from both apps.
_Both Quarkus and Spring use Micrometer as default metric provider._

Also add dashboards with id: 12962 for monitoring Axon

The Grafana instance has been configured with the Prometheus server as datasource. 
You can find this configuration in [prometheus_ds.yml](grafana/provisioning/datasources/prometheus_ds.yml).

## Application config

### Quarkus

To get Quarkus to expose metrics add the following dependency:

```xml
<dependencies>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-micrometer-registry-prometheus</artifactId>
    </dependency>
</dependencies>
```

### Spring

To get Spring to expose metrics add the following dependencies:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

and the following property:

```properties
management.endpoints.web.exposure.include=prometheus
```

# Sources

I used these guides to setup 
[Prometheus](https://medium.com/javarevisited/monitoring-setup-with-docker-compose-part-1-prometheus-3d2c9089ee82) and 
[Grafana](https://www.theairtips.com/post/setting-up-grafana-with-docker-compose). Quarkus and Spring are created without guides.