package com.murglin.consulting.invoice;

import com.murglin.consulting.invoice.api.HttpServerVerticle;
import com.murglin.consulting.invoice.api.crud.InvoiceRepositoryVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VertxApplication extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle(), VertxApplication::handleVerticleDeployment);
        vertx.deployVerticle(new InvoiceRepositoryVerticle(), VertxApplication::handleVerticleDeployment);
    }

    private static void handleVerticleDeployment(AsyncResult<String> result) {
        final var verticleName = result.result();
        if (result.succeeded()) {
            log.info("Successfully deployed verticle '{}'", verticleName);
        } else {
            log.info("Cannot deploy verticle '{}'", verticleName);
        }
    }
}
