package com.example.demo.axon.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoRepository extends JpaRepository<TodoView, UUID> {
}
