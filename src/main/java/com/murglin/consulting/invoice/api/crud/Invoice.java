package com.murglin.consulting.invoice.api.crud;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
public class Invoice {

    String name;
    String surname;
    BigDecimal amount;
    Currency currency;
}
