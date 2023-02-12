package com.example.demo.axon.query;

import com.example.demo.axon.api.TodoCompletedEvent;
import com.example.demo.axon.api.TodoCreatedEvent;
import com.example.demo.axon.api.TodoUncompletedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoProjector {

    private static final Logger log = LoggerFactory.getLogger(TodoProjector.class);

    private final TodoRepository repository;

    @Autowired
    public TodoProjector(TodoRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(TodoCreatedEvent event) {
        var view = new TodoView(event.uuid(), event.summary(), false);
        log.info("saving {} ", event.uuid());
        repository.save(view);
    }

    @EventHandler
    public void on(TodoCompletedEvent event) {
        repository.findById(event.todoId()).ifPresent(todo->{
            todo.setCompleted(true);
            repository.save(todo);
        });
    }

    @EventHandler
    public void on(TodoUncompletedEvent event) {
        repository.findById(event.todoId()).ifPresent(todo->{
            todo.setCompleted(false);
            repository.save(todo);
        });
    }

    @QueryHandler
    public TodoView find(FindTodoQuery query) {
        TodoView todoView = repository.findById(query.todoId()).orElse(null);
        return todoView;
    }

    @QueryHandler
    public List<TodoView> findAll(FindTodosQuery query) {
        return repository.findAll();
    }
}
