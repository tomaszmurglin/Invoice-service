package com.murglin.consulting.invoice.vertx;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.DatabindCodec;

public class Configuration {

    public static ConfigRetriever createConfigRetriever(Vertx vertx) {
        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path", "application-conf.json"));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStore).addStore(fileStore);
        return ConfigRetriever.create(vertx, options);
    }

    public static void configureJackson() {
        final var mapper = DatabindCodec.mapper();
        mapper.registerModule(new JavaTimeModule());
    }
}
