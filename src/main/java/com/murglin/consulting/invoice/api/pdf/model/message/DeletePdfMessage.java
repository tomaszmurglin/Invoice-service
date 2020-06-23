package com.murglin.consulting.invoice.api.pdf.model.message;

import com.murglin.consulting.invoice.api.pdf.model.command.PdfCommand;
import com.murglin.consulting.invoice.vertx.Message;
import io.vertx.core.json.JsonObject;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DeletePdfMessage extends Message {

    public DeletePdfMessage(UUID id, OffsetDateTime creationTimestamp, String sourceName, JsonObject payload) {
        super(id, PdfCommand.DELETE_PDF.getName(), creationTimestamp, sourceName, payload);
    }
}
