package com.murglin.consulting.invoice.api.pdf;

import com.murglin.consulting.invoice.api.RestController;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PdfRestController implements RestController {

    public void create(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started creating new pdf for invoice with id '{}'", id);

        log.info("Pdf for invoice with id '{}' created successfully", id);
    }

    public void find(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started finding pdf for the invoice with id '{}'", id);

        log.info("Pdf for invoice with id '{}' found successfully", id);
    }

    public void cancel(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started canceling creation of new pdf for the invoice with id '{}'", id);

        log.info("Cancelled successfully generation of new pdf for the invoice with id '{}'", id);
    }

    public void delete(final RoutingContext rc) {
        var id = rc.request().getParam("id");
        log.info("Started deleting pdf for the invoice with id '{}'", id);

        log.info("Deleted pdf for the invoice with id '{}' successfully", id);
    }
}
