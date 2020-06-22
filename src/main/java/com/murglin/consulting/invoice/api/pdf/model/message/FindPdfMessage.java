package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.api.pdf.model.command.PdfCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public class FindPdfMessage extends Message {

    public FindPdfMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, PdfCommand.FIND_PDF.getName());
    }
}
