package com.example.demo.axon.api;

import java.util.UUID;

public record TodoUncompletedEvent(UUID todoId) {
}
