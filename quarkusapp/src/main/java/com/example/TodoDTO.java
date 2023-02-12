package com.example;

import java.util.UUID;

public record TodoDTO(UUID uuid, String summary, boolean completed) {
}
