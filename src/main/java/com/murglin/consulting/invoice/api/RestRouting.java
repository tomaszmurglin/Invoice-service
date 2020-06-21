package com.murglin.consulting.invoice.api;

import com.murglin.consulting.invoice.api.crud.InvoiceRestController;
import com.murglin.consulting.invoice.api.pdf.PdfRestController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestRouting {

    public static Router createRouting(Vertx vertx) {
        Router router = Router.router(vertx);

        var pdfRestController = new PdfRestController();
        var invoiceRestController = new InvoiceRestController();

        router.post("/invoice").handler(invoiceRestController::create);
        router.get("/invoice/{id}").handler(invoiceRestController::find);
        router.delete("/invoice/{id}").handler(invoiceRestController::delete);
        router.put("/invoice/{id}").handler(invoiceRestController::replace);
        router.post("/invoice/{id}/createPdf").handler(pdfRestController::create);
        router.get("/invoice/{id}/pdf").handler(pdfRestController::find);

        router.errorHandler(500, rc -> {
            final JsonObject error = new JsonObject()
                    .put("Exception Message", rc.failure().getMessage());
            log.error("An exception occurred while processing a request." + rc.failure());
            rc.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
            rc.response().end(error.encode());
        });

        return router;
    }
}
