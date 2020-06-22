package com.murglin.consulting.invoice.api.crud.model.message;

import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public class DeleteInvoiceMessage extends Message {

    public DeleteInvoiceMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, "DeleteInvoiceMessage");
    }
}
