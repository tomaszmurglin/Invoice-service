package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.InMemoryStorage;
import com.murglin.consulting.invoice.api.crud.model.Invoice;
import com.murglin.consulting.invoice.api.crud.model.command.InvoiceCommand;
import com.murglin.consulting.invoice.vertx.Message;
import com.murglin.consulting.invoice.vertx.Repository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;

import java.util.UUID;

import static com.murglin.consulting.invoice.api.crud.exception.InvoiceNotFoundException.INVOICE_DOESNT_EXIST;

public class InvoiceRepositoryVerticle extends AbstractVerticle implements Repository {

    public static final String EVENT_BUSS_ADDRESS = "InvoiceRepositoryVerticle";
    private EventBus eventBus;

    @Override
    public void start() {
        eventBus = vertx.eventBus();
        eventBus.consumer(EVENT_BUSS_ADDRESS, message -> {
            final var messageRepresentation = Json.decodeValue((String) message.body(), Message.class);
            final var commandName = messageRepresentation.getName();
            if (InvoiceCommand.CREATE_INVOICE.getName().equals(commandName)) {
                handleCreate(messageRepresentation, message);
            }
            if (InvoiceCommand.DELETE_INVOICE.getName().equals(commandName)) {
                handleDelete(messageRepresentation, message);
            }
            if (InvoiceCommand.FIND_INVOICE.getName().equals(commandName)) {
                handleFind(messageRepresentation, message);
            }
            if (InvoiceCommand.REPLACE_INVOICE.getName().equals(commandName)) {
                handleReplace(messageRepresentation, message);
            }
        });
    }

    private void handleCreate(Message messageRepresentation, io.vertx.core.eventbus.Message<Object> message) {
        final var id = UUID.randomUUID();
        final var invoice = Invoice.ofMessage(messageRepresentation, id);
        InMemoryStorage.ID_TO_INVOICE.put(id, invoice);
        message.reply(Json.encode(invoice));
    }

    private void handleDelete(Message messageRepresentation, io.vertx.core.eventbus.Message<Object> message) {
        final var id = UUID.fromString(messageRepresentation.getPayload().getString("id"));
        if (!InMemoryStorage.ID_TO_INVOICE.containsKey(id)) {
            message.fail(404, INVOICE_DOESNT_EXIST);
        }
        final var invoice = InMemoryStorage.ID_TO_INVOICE.remove(id);
        message.reply(Json.encode(invoice));
    }

    private void handleFind(Message messageRepresentation, io.vertx.core.eventbus.Message<Object> message) {
        final var id = UUID.fromString(messageRepresentation.getPayload().getString("id"));
        if (!InMemoryStorage.ID_TO_INVOICE.containsKey(id)) {
            message.fail(404, INVOICE_DOESNT_EXIST);
        }
        final var invoice = InMemoryStorage.ID_TO_INVOICE.get(id);
        message.reply(Json.encode(invoice));
    }

    private void handleReplace(Message messageRepresentation, io.vertx.core.eventbus.Message<Object> message) {
        final var id = UUID.fromString(messageRepresentation.getPayload().getString("id"));
        if (!InMemoryStorage.ID_TO_INVOICE.containsKey(id)) {
            message.fail(404, INVOICE_DOESNT_EXIST);
        }
        final var invoice = InMemoryStorage.ID_TO_INVOICE.put(id, Invoice.ofMessage(messageRepresentation, id));
        message.reply(Json.encode(invoice));
    }
}
