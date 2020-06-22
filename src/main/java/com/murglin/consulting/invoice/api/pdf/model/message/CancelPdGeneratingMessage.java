package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.vertx.Message;

public class CancelPdGeneratingMessage extends Message {

    public CancelPdGeneratingMessage(String sourceName) {
        super(sourceName);
    }
}
