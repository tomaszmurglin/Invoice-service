package com.murglin.consulting.invoice.api.pdf;

import com.murglin.consulting.invoice.api.RestController;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
public class PdfRestController implements RestController {

    public void create(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started creating new pdf for invoice with id '{}'", invoiceId);
        throw new UnsupportedOperationException("Not implemented yet");
//        log.info("Pdf for invoice with id '{}' created successfully", invoiceId);
    }

    public void find(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started finding pdf for the invoice with id '{}'", invoiceId);
        throw new UnsupportedOperationException("Not implemented yet");
//        log.info("Pdf for invoice with id '{}' found successfully", invoiceId);
    }

    public void cancelGenerating(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started canceling creation of new pdf for the invoice with id '{}'", invoiceId);
        throw new UnsupportedOperationException("Not implemented yet");
//        log.info("Cancelled successfully generation of new pdf for the invoice with id '{}'", invoiceId);
    }

    public void delete(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started deleting pdf for the invoice with id '{}'", invoiceId);
        throw new UnsupportedOperationException("Not implemented yet");
//        log.info("Deleted pdf for the invoice with id '{}' successfully", invoiceId);
    }
}
