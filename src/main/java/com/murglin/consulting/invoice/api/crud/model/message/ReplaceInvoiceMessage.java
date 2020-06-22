package com.murglin.consulting.invoice.api.crud.model.message;

import com.murglin.consulting.invoice.api.crud.model.command.InvoiceCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public class ReplaceInvoiceMessage extends Message {

    public ReplaceInvoiceMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, InvoiceCommand.REPLACE_INVOICE.getName());
    }
}
