package com.murglin.consulting.invoice.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.DatabindCodec;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageDeserializer extends StdDeserializer<Message> {

    public MessageDeserializer() {
        this(null);
    }

    public MessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Message deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        final var mapper = DatabindCodec.mapper();

        JsonNode node = jp.getCodec().readTree(jp);
        var id = UUID.fromString(node.get("id").asText());
        var creationTimestamp = mapper.readValue(node.get("creationTimestamp").asText(), OffsetDateTime.class);
        var name = node.get("name").asText();
        var sourceName = node.get("sourceName").asText();
        var payload = new JsonObject(node.get("payload").toString());
        return new Message(id, name, creationTimestamp, sourceName, payload);
    }
}
