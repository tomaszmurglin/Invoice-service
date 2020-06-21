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

    private static final String JSON_UTF8 = "application/json; charset=UTF-8";
    private static final String APP_PDF = "application/pdf";

    public static Router createRouting(Vertx vertx) {
        Router router = Router.router(vertx);

        var pdfRestController = new PdfRestController();
        var invoiceRestController = new InvoiceRestController();

        //invoice crud
        router.post("/invoice").handler(invoiceRestController::create).produces(JSON_UTF8);
        router.get("/invoice/:id").handler(invoiceRestController::find).produces(JSON_UTF8);
        router.delete("/invoice/:id").handler(invoiceRestController::delete).produces(JSON_UTF8);
        router.put("/invoice/:id").handler(invoiceRestController::replace).produces(JSON_UTF8);

        //pdf
        router.post("/invoice/:id/createPdf").handler(pdfRestController::create).produces(JSON_UTF8);
        router.get("/invoice/:id/pdf").handler(pdfRestController::find).produces(APP_PDF);
        router.delete("/invoice/:id/pdf").handler(pdfRestController::delete).produces(JSON_UTF8);
        router.post("/invoice/:id/cancelPdf").handler(pdfRestController::cancel).produces(JSON_UTF8);

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
