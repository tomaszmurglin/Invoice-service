package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.InMemoryStorage;
import com.murglin.consulting.invoice.api.crud.model.Invoice;
import com.murglin.consulting.invoice.vertx.Repository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;

import java.util.UUID;

public class InvoiceRepositoryVerticle extends AbstractVerticle implements Repository {

    public static final String EVENT_BUSS_ADDRESS = "InvoiceRepositoryVerticle";
    private final EventBus eventBus = Vertx.currentContext().owner().eventBus();

    @Override
    public void start() {
        eventBus.consumer(EVENT_BUSS_ADDRESS, message -> {
            final var invoice = Json.decodeValue((String) message.body(), Invoice.class);
            InMemoryStorage.ID_TO_INVOICE.put(UUID.randomUUID(), invoice);
            message.reply(message.body());
        });
    }
}
