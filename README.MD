# Usage

First build the runnable jars for quarkus and spring
```bash
mvn package
```

Then start the applications and prometheus
```bash
docker compose up
```

Open http://localhost:9000 to open the Prometheus explorer. Search for metrics, i.e.
 - jvm_memory_committed_bytes
 - jvm_memory_max_bytes

For all **quarkus** metrics visit http://localhost:8000/q/metrics

For all **spring** metrics visit http://localhost:8001/actuator/prometheus