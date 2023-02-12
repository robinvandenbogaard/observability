package com.example.demo.axon.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record UncompleteTodoCommand(@TargetAggregateIdentifier UUID todoId) {
}
