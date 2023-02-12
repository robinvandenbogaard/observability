package com.example.demo.rest;

import com.example.demo.axon.api.CompleteTodoCommand;
import com.example.demo.axon.api.CreateTodoCommand;
import com.example.demo.axon.api.UncompleteTodoCommand;
import com.example.demo.axon.query.FindTodoQuery;
import com.example.demo.axon.query.FindTodosQuery;
import com.example.demo.axon.query.TodoView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class TodoEndpoint {

    private static final Logger log = LoggerFactory.getLogger(TodoEndpoint.class);

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public TodoEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }
    
    @PostMapping(path = "create")
    public ResponseEntity<Void> createNew() {
        log.info("created");
        commandGateway.send(new CreateTodoCommand("banaan"));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "complete/{todoId}")
    public ResponseEntity<Void> complete(@PathVariable("todoId") UUID todoId) {
        log.info("complete");
        commandGateway.send(new CompleteTodoCommand(todoId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "uncomplete/{todoId}")
    public ResponseEntity<Void> uncomplete(@PathVariable("todoId") UUID todoId) {
        log.info("uncomplete");
        commandGateway.send(new UncompleteTodoCommand(todoId));
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "todo/{todoId}")
    public CompletableFuture<TodoView> handle(@PathVariable("todoId") UUID todoId) {
        log.info("get");
        return queryGateway.query(new FindTodoQuery(todoId), ResponseTypes.instanceOf(TodoView.class));
    }

    @GetMapping(path = "todos")
    public String handle() throws ExecutionException, InterruptedException, JsonProcessingException {
        List<TodoView> todoViews = queryGateway.query(new FindTodosQuery(), ResponseTypes.multipleInstancesOf(TodoView.class)).get();
        String result = new ObjectMapper().writeValueAsString(todoViews);
        log.info("get {}", result);
        return result;
    }
}
