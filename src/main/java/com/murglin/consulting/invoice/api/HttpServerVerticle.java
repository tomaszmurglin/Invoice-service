package com.murglin.consulting.invoice.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j2;

import static com.murglin.consulting.invoice.vertx.Configuration.createConfigRetriever;
import static com.murglin.consulting.invoice.api.RestRouting.createRouting;

@Log4j2
public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start() {
        final var appConfigRetriever = createConfigRetriever(vertx);
        appConfigRetriever.getConfig(readConfigResult -> {
            if (readConfigResult.succeeded()) {
                startHttpServer(readConfigResult);
            } else {
                log.error("Cannot read configuration file", readConfigResult.cause());
            }
        });
    }

    private void startHttpServer(AsyncResult<JsonObject> readConfigResult) {
        var port = readConfigResult.result().getInteger("http.port");
        final var routing = createRouting(vertx);
        vertx.createHttpServer().requestHandler(routing).listen(port, createServerResult -> {
            if (createServerResult.succeeded()) {
                log.info("Http server has started and listening on port '{}'", port);
            } else {
                log.error("Http server has not started", createServerResult.cause());
            }
        });
    }
}
