package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.vertx.Message;

public class CreatePdfMessage extends Message {

    public CreatePdfMessage(String sourceName) {
        super(sourceName);
    }
}
