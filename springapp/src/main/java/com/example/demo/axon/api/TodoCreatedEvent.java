package com.example.demo.axon.api;

import java.util.Objects;
import java.util.UUID;

public record TodoCreatedEvent(UUID uuid, String summary) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TodoCreatedEvent) obj;
        return Objects.equals(this.uuid, that.uuid) &&
                Objects.equals(this.summary, that.summary);
    }

    @Override
    public String toString() {
        return "TodoCreatedEvent[" +
                "uuid=" + uuid + ", " +
                "summary=" + summary + ']';
    }

}
