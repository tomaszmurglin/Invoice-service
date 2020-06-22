package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.vertx.Message;

public class FindPdfMessage extends Message {

    public FindPdfMessage(String sourceName) {
        super(sourceName);
    }
}
