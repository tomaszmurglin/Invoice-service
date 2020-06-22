package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.api.pdf.model.command.PdfCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public class DeletePdfMessage extends Message {

    public DeletePdfMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, PdfCommand.DELETE_PDF.getName());
    }
}
