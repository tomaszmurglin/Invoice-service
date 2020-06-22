package com.murglin.consulting.invoice.api.crud.model.message;

import com.murglin.consulting.invoice.api.crud.model.command.InvoiceCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public final class CreateInvoiceMessage extends Message {

    public CreateInvoiceMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, InvoiceCommand.CREATE_INVOICE.getName());
    }
}
