package com.murglin.consulting.invoice.api.crud.model.message;

import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public class FindInvoiceMessage extends Message {

    public FindInvoiceMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, "DeleteInvoiceMessage");
    }
}
