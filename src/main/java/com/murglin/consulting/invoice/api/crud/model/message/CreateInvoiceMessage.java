package com.murglin.consulting.invoice.api.crud.model.message;

import com.murglin.consulting.invoice.api.crud.model.command.InvoiceCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

import java.time.OffsetDateTime;
import java.util.UUID;

public final class CreateInvoiceMessage extends Message {

    public CreateInvoiceMessage(UUID id, OffsetDateTime creationTimestamp, String sourceName, JsonObject payload) {
        super(id, InvoiceCommand.CREATE_INVOICE.getName(), creationTimestamp, sourceName, payload);
    }
}
