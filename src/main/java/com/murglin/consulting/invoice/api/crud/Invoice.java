package com.murglin.consulting.invoice.api.crud;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Invoice {

    String name;
    String surname;
    BigDecimal amount;
}
