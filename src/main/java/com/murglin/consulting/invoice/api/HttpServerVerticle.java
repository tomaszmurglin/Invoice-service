package com.murglin.consulting.invoice.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j2;

import static com.murglin.consulting.invoice.api.RestRouting.createRouting;
import static com.murglin.consulting.invoice.vertx.Configuration.createConfigRetriever;

@Log4j2
public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        final var appConfigRetriever = createConfigRetriever(vertx);
        appConfigRetriever.getConfig(readConfigResult -> {
            if (readConfigResult.succeeded()) {
                startHttpServer(readConfigResult, startPromise);
            } else {
                log.error("Cannot read configuration file", readConfigResult.cause());
                startPromise.fail(readConfigResult.cause());
            }
        });
    }

    private void startHttpServer(AsyncResult<JsonObject> readConfigResult, Promise<Void> startPromise) {
        var port = readConfigResult.result().getInteger("http.port");
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
