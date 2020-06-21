package com.murglin.consulting.invoice.api.pdf;

import com.murglin.consulting.invoice.vertx.Service;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

public class PdfServiceVerticle extends AbstractVerticle implements Service {

    public static final String EVENT_BUSS_ADDRESS = "PdfServiceVerticle";

    public void create(RoutingContext rc, UUID invoiceId) {

    }

    public void find(RoutingContext rc, UUID invoiceId) {

    }

    public void cancel(RoutingContext rc, UUID invoiceId) {

    }

    public void delete(RoutingContext rc, UUID invoiceId) {

    }
}
