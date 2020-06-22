package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.InMemoryStorage;
import com.murglin.consulting.invoice.api.crud.exception.InvoiceCreationException;
import com.murglin.consulting.invoice.api.crud.model.Invoice;
import com.murglin.consulting.invoice.vertx.Service;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

public class InvoiceServiceVerticle extends AbstractVerticle implements Service {

    public static final String EVENT_BUSS_ADDRESS = "InvoiceServiceVerticle";
    private final EventBus eventBus = Vertx.currentContext().owner().eventBus();

    @Override
    public void start() {
        eventBus.consumer(EVENT_BUSS_ADDRESS, message -> {
            //TODO move to the repository
            final var invoice = Json.decodeValue((String) message.body(), Invoice.class);
            InMemoryStorage.ID_TO_INVOICE.put(UUID.randomUUID(), invoice);
            message.reply(message.body());
        });
    }

    private void replace(RoutingContext rc, UUID invoiceId) {
        //TODO check if exits and if not map exception to 404
    }

    private void delete(RoutingContext rc, UUID invoiceId) {
        //TODO check if exits and if not map exception to 404
    }

    private void find(RoutingContext rc, UUID invoiceId) {
        //TODO check if exits and if not map exception to 404
    }

    private void create(RoutingContext rc, JsonObject invoiceAsJson) {
        eventBus.request(InvoiceRepositoryVerticle.EVENT_BUSS_ADDRESS, invoiceAsJson, result -> {
            if (result.succeeded()) {
                rc.response().setStatusCode(201).end(invoiceAsJson.encode());
            } else {
                throw new InvoiceCreationException(result.cause());
            }
        });
    }
}
