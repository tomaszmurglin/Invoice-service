package com.murglin.consulting.invoice;

import com.murglin.consulting.invoice.api.HttpServerVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class VertxApplication extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle());
    }
}
