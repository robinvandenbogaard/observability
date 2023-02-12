package com.example.demo.axon.command;

import com.example.demo.axon.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

@Aggregate
public class Todo {

    @AggregateIdentifier
    private UUID id;
    private String summary;
    private boolean completed;

    public Todo() {
        //Required by Axon
    }

    @CommandHandler
    public Todo(CreateTodoCommand cmd) {
        apply(new TodoCreatedEvent(UUID.randomUUID(), cmd.summary()));
    }

    @EventSourcingHandler
    public void created(TodoCreatedEvent evt) {
        id = evt.uuid();
        summary = evt.summary();
        completed = false;
    }

    @CommandHandler
    void complete(CompleteTodoCommand cmd) {
        apply(new TodoCompletedEvent(cmd.todoId()));
    }

    @EventSourcingHandler
    public void completed(TodoCompletedEvent event) {
        this.completed = true;
    }

    @CommandHandler
    void uncomplete(UncompleteTodoCommand cmd) {
        apply(new TodoUncompletedEvent(cmd.todoId()));
    }

    @EventSourcingHandler
    public void uncompleted(TodoUncompletedEvent event) {
        this.completed = false;
    }


}
