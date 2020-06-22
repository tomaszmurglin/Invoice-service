package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.api.pdf.model.command.PdfCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

public class CancelPdGeneratingMessage extends Message {

    public CancelPdGeneratingMessage(String sourceName, JsonObject payload) {
        super(sourceName, payload, PdfCommand.CANCEL_PDF_GENERATING.getName());
    }
}
