package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.crud.exception.InvoiceCreationException;
import com.murglin.consulting.invoice.vertx.Service;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

public class InvoiceServiceVerticle extends AbstractVerticle implements Service {

    public static final String EVENT_BUSS_ADDRESS = "InvoiceServiceVerticle";
    private final EventBus eventBus = Vertx.currentContext().owner().eventBus();

    public void replace(RoutingContext rc, UUID invoiceId) {

    }

    public void delete(RoutingContext rc, UUID invoiceId) {

    }

    public void find(RoutingContext rc, UUID invoiceId) {

    }

    public void create(RoutingContext rc, JsonObject invoiceAsJson) {
        eventBus.request(InvoiceRepositoryVerticle.EVENT_BUSS_ADDRESS, invoiceAsJson, result -> {
            if (result.succeeded()) {
                rc.response().setStatusCode(201).end(invoiceAsJson.encode());
            } else {
                throw new InvoiceCreationException(result.cause());
            }
        });
    }
}
