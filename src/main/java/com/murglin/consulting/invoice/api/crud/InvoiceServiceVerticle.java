package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.crud.model.command.InvoiceCommand;
import com.murglin.consulting.invoice.vertx.Message;
import com.murglin.consulting.invoice.vertx.Service;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;

public class InvoiceServiceVerticle extends AbstractVerticle implements Service {

    public static final String EVENT_BUSS_ADDRESS = "InvoiceServiceVerticle";
    private EventBus eventBus;

    @Override
    public void start() {
        eventBus = vertx.eventBus();
        eventBus.consumer(EVENT_BUSS_ADDRESS, message -> {
            final var messageRepresentation = Json.decodeValue((String) message.body(), Message.class);
            final var commandName = messageRepresentation.getName();
            if (InvoiceCommand.CREATE_INVOICE.getName().equals(commandName)) {
                create(message);
            }
            if (InvoiceCommand.DELETE_INVOICE.getName().equals(commandName)) {
                delete(message);
            }
            if (InvoiceCommand.FIND_INVOICE.getName().equals(commandName)) {
                find(message);
            }
            if (InvoiceCommand.REPLACE_INVOICE.getName().equals(commandName)) {
                replace(message);
            }
            message.reply(message.body());
        });
    }

    private void create(io.vertx.core.eventbus.Message<Object> message) {
        eventBus.request(InvoiceRepositoryVerticle.EVENT_BUSS_ADDRESS, message.body(), responseFromRepositoryVerticle -> {
            if (responseFromRepositoryVerticle.succeeded()) {
                message.reply(responseFromRepositoryVerticle.result());
            } else {
                message.fail(500, responseFromRepositoryVerticle.cause().toString());
            }
        });
    }

    private void replace(io.vertx.core.eventbus.Message<Object> message) {
        //TODO check if exits and if not map exception to 404
    }

    private void delete(io.vertx.core.eventbus.Message<Object> message) {
        //TODO check if exits and if not map exception to 404
    }

    private void find(io.vertx.core.eventbus.Message<Object> message) {
        //TODO check if exits and if not map exception to 404
    }
}
