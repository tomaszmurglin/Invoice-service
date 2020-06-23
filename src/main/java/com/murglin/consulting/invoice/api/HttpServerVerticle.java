package com.murglin.consulting.invoice.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.log4j.Log4j2;

import static com.murglin.consulting.invoice.api.RestRouting.createRouting;

@Log4j2
public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        startHttpServer(startPromise);
    }

    private void startHttpServer(Promise<Void> startPromise) {
        var port = config().getInteger("http.port");
        final var routing = createRouting(vertx);
        vertx.createHttpServer().requestHandler(routing).listen(port, createServerResult -> {
            if (createServerResult.succeeded()) {
                log.info("Http server has started and listening on port '{}'", port);
                startPromise.complete();
            } else {
                log.error("Http server has not started", createServerResult.cause());
                startPromise.fail(createServerResult.cause());
            }
        });
    }
}
