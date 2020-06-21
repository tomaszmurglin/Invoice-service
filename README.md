# Vertx-invoice-service
Sample reactive web app in vertx and java.
App uses actor concurrency model (http://tutorials.jenkov.com/java-concurrency/concurrency-models.html#actors-vs-channels)
so in vertx actor is called verticle. The goal of this project is learning about Vertx framework.

# Features:
* crud of invoice
* PDF generation of the given invoice

# Tech stack:
* Vertx
* Java 11
* log4j2 for non-blocking logging
* Gradle

# Building and running
* build: gradle build
* run from the project root catalog: java -jar build/vertx-invoice-service.jar

# TODO:
* hot deploy in dev mode
* ci/cd integration
* dockerization
* api doc