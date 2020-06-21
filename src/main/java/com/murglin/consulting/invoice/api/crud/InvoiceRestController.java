package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.RestController;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
public class InvoiceRestController implements RestController {

    private final InvoiceServiceVerticle service;

    public void create(final RoutingContext rc) {
        log.info("Started creating new invoice");
        final var invoiceAsJson = Optional.ofNullable(rc.getBodyAsJson()).orElseThrow(IllegalArgumentException::new);
        InvoiceValidator.validate(invoiceAsJson.getString("name"), invoiceAsJson.getString("surname"), new BigDecimal(invoiceAsJson.getString("amount")));
        service.create(rc, invoiceAsJson);
        log.info("Invoice '{}' created successfully", invoice);
    }

    public void replace(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started replacing new invoice with id '{}'", invoiceId);
        service.replace(rc, invoiceId);
        log.info("Invoice with id '{}' replaced successfully", invoiceId);
    }

    public void delete(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started deleting new invoice with id '{}'", invoiceId);
        service.delete(rc, invoiceId);
        log.info("Invoice with id '{}' deleted successfully", invoiceId);
    }

    public void find(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started finding new invoice with id '{}'", invoiceId);
        service.find(rc, invoiceId);
        log.info("Invoice with id '{}' found successfully", invoiceId);
    }
}
