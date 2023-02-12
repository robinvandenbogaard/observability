package com.example;

import io.quarkus.scheduler.Scheduled;
import io.vertx.core.impl.ConcurrentHashSet;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.util.Random;

@ApplicationScoped
public class Completionist {

    private static final Logger log = LoggerFactory.getLogger(Completionist.class);

    private TodoApp client;

    private ConcurrentHashSet<TodoDTO> todos;

    private static Random R = new Random();

    @ConfigProperty(name = "todo.client.host", defaultValue = "localhost")
    private String host;

    @PostConstruct
    void init() {
        todos = new ConcurrentHashSet<>();
        client = RestClientBuilder.newBuilder().baseUri(URI.create("http://"+host+":8080")).build(TodoApp.class);
    }

    @Scheduled(every="8s")
    void setup() {
        log.info("creating");
        client.createNew();
    }

    @Scheduled(every="10s")
    void retrieve() {
        var list = client.getAll();
        log.info("got {} todos", list.size());
        todos.addAll(list);
    }

    @Scheduled(every="1s")
    void spam() {
        var items = todos.stream().limit(50).toList();
        items.forEach(item-> {
            if (R.nextBoolean()) {
                client.complete(item.uuid());
            } else {
                client.uncomplete(item.uuid());
            }
        });
    }
}
