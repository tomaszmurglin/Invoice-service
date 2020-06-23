package com.murglin.consulting.invoice.vertx;

import io.vertx.core.json.JsonObject;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public abstract class Message implements Stereotype {

    private final UUID id;
    private final String name;
    private final OffsetDateTime timestamp;
    private final String sourceName;
    private final JsonObject payload;

    public Message(String sourceName, JsonObject payload, String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.timestamp = OffsetDateTime.now();
        this.sourceName = sourceName;
        this.payload = payload;
    }
}
