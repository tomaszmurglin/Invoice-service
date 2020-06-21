# Vertx-invoice-service
Sample reactive web app in vertx and java.
App uses actor concurrency model (http://tutorials.jenkov.com/java-concurrency/concurrency-models.html#actors-vs-channels).
In vertx actor is called verticle. The goal of this project is learning about Vertx framework. Theres no persistence layer.

# Features:
* CRUD of the invoice
* PDF generation of the given invoice

# Tech stack:
* Vertx
* Java 11
* Log4j2 for non-blocking logging
* Gradle

# Building and running
* Build: configure gradle.properties and gradle build
* Run: run from the project root catalog: java -jar build/vertx-invoice-service.jar -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
* Debug on 5005: run from the project root catalog: java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector build/vertx-invoice-service.jar

# TODO:
* Tests
* Replace InMemoryStorage with h2 + reactive client
* Hot deploy in dev mode
* Ci/cd integration
* Dockerization
* Api doc
* CORS setup