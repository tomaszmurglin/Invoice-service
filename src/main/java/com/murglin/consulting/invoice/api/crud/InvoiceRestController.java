package com.murglin.consulting.invoice.api.crud;

import com.murglin.consulting.invoice.api.RestController;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvoiceRestController implements RestController {

    public void create(final RoutingContext rc) {
        log.info("Started creating new invoice");

        log.info("Invoice '{}' created successfully", invoice);
    }

    public void replace(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started replacing new invoice with id '{}'", id);

        log.info("Invoice with id '{}' replaced successfully", id);
    }

    public void delete(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started deleting new invoice with id '{}'", id);

        log.info("Invoice with id '{}' deleted successfully", id);
    }

    public void find(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started finding new invoice with id '{}'", id);

        log.info("Invoice with id '{}' found successfully", id);
    }
}
