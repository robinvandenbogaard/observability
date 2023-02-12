package com.example.demo.axon.api;

import java.util.UUID;

public record TodoCompletedEvent(UUID todoId) {
}
