package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.RestController;
import com.murglin.consulting.invoice.api.crud.exception.InvoiceCreationException;
import com.murglin.consulting.invoice.api.crud.exception.InvoiceDeletingException;
import com.murglin.consulting.invoice.api.crud.exception.InvoiceFindingException;
import com.murglin.consulting.invoice.api.crud.exception.InvoiceReplacingException;
import com.murglin.consulting.invoice.api.crud.model.message.CreateInvoiceMessage;
import com.murglin.consulting.invoice.api.crud.model.message.DeleteInvoiceMessage;
import com.murglin.consulting.invoice.api.crud.model.message.FindInvoiceMessage;
import com.murglin.consulting.invoice.api.crud.model.message.ReplaceInvoiceMessage;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
public class InvoiceRestController implements RestController {

    private final EventBus eventBus = Vertx.currentContext().owner().eventBus();

    public void create(final RoutingContext rc) {
        log.info("Started creating new invoice");
        final var invoiceAsJson = Optional.ofNullable(rc.getBodyAsJson()).orElseThrow(IllegalArgumentException::new);
        InvoiceValidator.validate(invoiceAsJson.getString("name"), invoiceAsJson.getString("surname"), new BigDecimal(invoiceAsJson.getString("amount")),
                invoiceAsJson.getString("currencyCode"));
        final var message = new CreateInvoiceMessage(UUID.randomUUID(), OffsetDateTime.now(), this.getClass().getName(), invoiceAsJson);
        eventBus.request(InvoiceServiceVerticle.EVENT_BUSS_ADDRESS, Json.encode(message), response -> {
            if (response.succeeded()) {
                final var createdInvoice = (String) Json.decodeValue((String) response.result().body());
                log.info("Created successfully invoice '{}'", createdInvoice);
                rc.response().setStatusCode(201).end(new JsonObject(createdInvoice).encode());
            } else {
                log.error("Invoice creation failed", response.cause());
                throw new InvoiceCreationException(response.cause());
            }
        });
    }

    public void replace(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started replacing invoice with id '{}'", invoiceId);
        final var invoiceAsJson = Optional.ofNullable(rc.getBodyAsJson()).orElseThrow(IllegalArgumentException::new);
        InvoiceValidator.validate(invoiceAsJson.getString("name"), invoiceAsJson.getString("surname"), new BigDecimal(invoiceAsJson.getString("amount")),
                invoiceAsJson.getString("currencyCode"));
        final var message = new ReplaceInvoiceMessage(UUID.randomUUID(), OffsetDateTime.now(), this.getClass().getName(), invoiceAsJson.put("id", invoiceId.toString()));
        eventBus.request(InvoiceServiceVerticle.EVENT_BUSS_ADDRESS, Json.encode(message), response -> {
            if (response.succeeded()) {
                final var replacedInvoice = (String) Json.decodeValue((String) response.result().body());
                log.info("Replaced successfully invoice with id '{}'", invoiceId);
                rc.response().setStatusCode(200).end(replacedInvoice);
            } else {
                final var cause = response.cause();
                if (cause instanceof ReplyException) {
                    final var failureCode = ((ReplyException) cause).failureCode();
                    if (failureCode == 404) {
                        log.info("Invoice with id '{}' has not been found", invoiceId);
                        rc.response().setStatusCode(404).end("Invoice not found");
                    }
                } else {
                    log.error("Invoice replacing failed", response.cause());
                    throw new InvoiceReplacingException(response.cause());
                }
            }
        });
    }

    public void delete(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started deleting invoice with id '{}'", invoiceId);
        final var message = new DeleteInvoiceMessage(UUID.randomUUID(), OffsetDateTime.now(), this.getClass().getName(), new JsonObject().put("id", invoiceId.toString()));
        eventBus.request(InvoiceServiceVerticle.EVENT_BUSS_ADDRESS, Json.encode(message), response -> {
            if (response.succeeded()) {
                final var deletedInvoice = (String) Json.decodeValue((String) response.result().body());
                log.info("Deleted successfully invoice with id '{}'", invoiceId);
                rc.response().setStatusCode(200).end(deletedInvoice);
            } else {
                final var cause = response.cause();
                if (cause instanceof ReplyException) {
                    final var failureCode = ((ReplyException) cause).failureCode();
                    if (failureCode == 404) {
                        log.info("Invoice with id '{}' has not been found", invoiceId);
                        rc.response().setStatusCode(404).end("Invoice not found");
                    }
                } else {
                    log.error("Invoice deleting failed", response.cause());
                    throw new InvoiceDeletingException(response.cause());
                }
            }
        });
    }

    public void find(final RoutingContext rc) {
        var invoiceId = UUID.fromString(rc.request().getParam("id"));
        log.info("Started finding invoice with id '{}'", invoiceId);
        final var message = new FindInvoiceMessage(UUID.randomUUID(), OffsetDateTime.now(), this.getClass().getName(), new JsonObject().put("id", invoiceId.toString()));
        eventBus.request(InvoiceServiceVerticle.EVENT_BUSS_ADDRESS, Json.encode(message), response -> {
            if (response.succeeded()) {
                final var foundInvoice = (String) Json.decodeValue((String) response.result().body());
                log.info("Found successfully invoice with id '{}'", invoiceId);
                rc.response().setStatusCode(200).end(foundInvoice);
            } else {
                final var cause = response.cause();
                if (cause instanceof ReplyException) {
                    final var failureCode = ((ReplyException) cause).failureCode();
                    if (failureCode == 404) {
                        log.info("Invoice with id '{}' has not been found", invoiceId);
                        rc.response().setStatusCode(404).end("Invoice not found");
                    }
                } else {
                    log.error("Invoice finding failed", response.cause());
                    throw new InvoiceFindingException(response.cause());
                }
            }
        });
    }
}
