package com.murglin.consulting.invoice.api.pdf;

import com.murglin.consulting.invoice.vertx.Repository;
import io.vertx.core.AbstractVerticle;

public class PdfRepositoryVerticle extends AbstractVerticle implements Repository {

    public static final String EVENT_BUSS_ADDRESS = "PdfRepositoryVerticle";
}
