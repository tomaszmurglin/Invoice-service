package com.murglin.consulting.invoice.vertx;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.murglin.consulting.invoice.vertx.jackson.MessageDeserializer;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = MessageDeserializer.class)
public class Message implements Stereotype {

    private UUID id;
    private String name;
    private OffsetDateTime creationTimestamp;
    private String sourceName;
    private JsonObject payload;
}
