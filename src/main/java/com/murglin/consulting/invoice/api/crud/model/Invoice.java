package com.murglin.consulting.invoice.api.crud.model;

import com.murglin.consulting.invoice.vertx.Message;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Value
public class Invoice {

    UUID id;
    String name;
    String surname;
    BigDecimal amount;
    Currency currency;

    public static Invoice ofMessage(Message messageRepresentation, UUID id) {
        final var name = messageRepresentation.getPayload().getString("name");
        final var surname = messageRepresentation.getPayload().getString("surname");
        final var amount = new BigDecimal(messageRepresentation.getPayload().getString("amount"));
        final var currency = Currency.getInstance(messageRepresentation.getPayload().getString("currencyCode"));
        return new Invoice(id, name, surname, amount, currency);
    }
}
