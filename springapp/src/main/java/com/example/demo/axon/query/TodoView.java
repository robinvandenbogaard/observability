package com.example.demo.axon.query;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TodoView {

    @Id
    private final UUID uuid;

    private final String summary;

    private boolean completed;

    public TodoView() {
        this(null, null, false);
    }

    public TodoView(UUID uuid, String summary, boolean completed) {
        this.uuid = uuid;
        this.summary = summary;
        this.completed = completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isCompleted() {
        return completed;
    }
}
