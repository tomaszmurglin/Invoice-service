package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.api.pdf.model.command.PdfCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CancelPdGeneratingMessage extends Message {

    public CancelPdGeneratingMessage(UUID id, OffsetDateTime creationTimestamp, String sourceName, JsonObject payload) {
        super(id, PdfCommand.CANCEL_PDF_GENERATING.getName(), creationTimestamp, sourceName, payload);
    }
}
