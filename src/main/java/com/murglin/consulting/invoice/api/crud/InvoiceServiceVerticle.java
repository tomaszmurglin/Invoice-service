package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.crud.model.command.InvoiceCommand;
import com.murglin.consulting.invoice.vertx.Message;
import com.murglin.consulting.invoice.vertx.Service;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.ReplyException;
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
        });
    }

    private void create(io.vertx.core.eventbus.Message<Object> message) {
        forwardMessageToRepository(message);
    }

    private void replace(io.vertx.core.eventbus.Message<Object> message) {
        forwardMessageToRepository(message);
    }

    private void delete(io.vertx.core.eventbus.Message<Object> message) {
        forwardMessageToRepository(message);
    }

    private void find(io.vertx.core.eventbus.Message<Object> message) {
        forwardMessageToRepository(message);
    }

    private void forwardMessageToRepository(io.vertx.core.eventbus.Message<Object> message) {
        eventBus.request(InvoiceRepositoryVerticle.EVENT_BUSS_ADDRESS, message.body(), responseFromRepositoryVerticle -> {
            if (responseFromRepositoryVerticle.succeeded()) {
                message.reply(Json.encode(responseFromRepositoryVerticle.result().body()));
            } else {
                final var cause = responseFromRepositoryVerticle.cause();
                if (cause instanceof ReplyException) {
                    message.fail(((ReplyException) cause).failureCode(), cause.getMessage());
                } else {
                    message.fail(500, responseFromRepositoryVerticle.cause().toString());
                }
            }
        });
    }
}
