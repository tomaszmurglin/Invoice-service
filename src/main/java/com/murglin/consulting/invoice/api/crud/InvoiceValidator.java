package com.murglin.consulting.invoice.api.crud;

import io.netty.util.internal.StringUtil;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.Currency;

@Log4j2
public class InvoiceValidator {
    public static void validate(String name, String surname, BigDecimal amount, String currencyCode) {
        if (amount.signum() < 0) {
            log.warn("Negative amount provided");
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        Currency.getInstance(currencyCode);//throws on invalid
        if (StringUtil.isNullOrEmpty(name) || name.isBlank()) {
            log.warn("Blank name provided");
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (StringUtil.isNullOrEmpty(surname) || surname.isBlank()) {
            log.warn("Blank surname provided");
            throw new IllegalArgumentException("Surname cannot be blank");
        }
    }
}
