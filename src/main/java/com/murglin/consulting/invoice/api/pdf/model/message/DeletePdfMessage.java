package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.vertx.Message;

public class DeletePdfMessage extends Message {

    public DeletePdfMessage(String sourceName) {
        super(sourceName);
    }
}
