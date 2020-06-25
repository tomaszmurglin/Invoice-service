package com.murglin.consulting.invoice;

import com.murglin.consulting.invoice.api.HttpServerVerticle;
import com.murglin.consulting.invoice.api.crud.InvoiceRepositoryVerticle;
import com.murglin.consulting.invoice.api.crud.InvoiceServiceVerticle;
import com.murglin.consulting.invoice.api.pdf.PdfRepositoryVerticle;
import com.murglin.consulting.invoice.api.pdf.PdfServiceVerticle;
import com.murglin.consulting.invoice.vertx.Configuration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.log4j.Log4j2;

import static com.murglin.consulting.invoice.vertx.Configuration.createConfigRetriever;

@Log4j2
public class VertxApplication extends AbstractVerticle {

    public static void main(String[] args) {
        //check that async non-blocking logger is used
        final var log4jContextSelector = System.getProperty("Log4jContextSelector");
        if (!"org.apache.logging.log4j.core.async.AsyncLoggerContextSelector".equals(log4jContextSelector)) {
            throw new IllegalStateException("Blocking logger detected. Please use non - blocking logger in reactive app");
        }

        Vertx vertx = Vertx.vertx();

        Configuration.configureJackson();

        final var appConfigRetriever = createConfigRetriever(vertx);
        appConfigRetriever.getConfig(readConfigResult -> {
            if (readConfigResult.succeeded()) {
                final var config = readConfigResult.result();
                //http server
                vertx.deployVerticle(HttpServerVerticle.class, new DeploymentOptions().setInstances(Runtime.getRuntime().availableProcessors()).setConfig(config),
                        VertxApplication::handleVerticleDeployment);

                //invoice
                vertx.deployVerticle(InvoiceServiceVerticle.class, new DeploymentOptions().setConfig(config), VertxApplication::handleVerticleDeployment);
                vertx.deployVerticle(InvoiceRepositoryVerticle.class, new DeploymentOptions().setConfig(config), VertxApplication::handleVerticleDeployment);

                //pdf
                vertx.deployVerticle(PdfServiceVerticle.class, new DeploymentOptions().setConfig(config), VertxApplication::handleVerticleDeployment);
                vertx.deployVerticle(PdfRepositoryVerticle.class, new DeploymentOptions().setConfig(config), VertxApplication::handleVerticleDeployment);
            } else {
                log.error("Cannot read configuration file", readConfigResult.cause());
                throw new IllegalStateException("Cannot read configuration file", readConfigResult.cause());
            }
        });
    }

    private static void handleVerticleDeployment(AsyncResult<String> result) {
        final var verticleName = result.result();
        if (result.succeeded()) {
            log.info("Successfully deployed verticle '{}'", verticleName);
        } else {
            log.error("Cannot deploy verticle '{}'", verticleName);
            throw new IllegalStateException();
        }
    }
}
