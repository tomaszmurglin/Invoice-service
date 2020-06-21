package com.murglin.consulting.invoice.vertx;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Configuration {

    public static ConfigRetriever createConfigRetriever(Vertx vertx) {
        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path", "application-conf.json"));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStore).addStore(fileStore);
        return ConfigRetriever.create(vertx, options);
    }
}
