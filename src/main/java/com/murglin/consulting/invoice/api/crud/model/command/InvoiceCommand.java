package com.murglin.consulting.invoice.api.crud.model.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InvoiceCommand {

    CREATE_INVOICE("CreateInvoice"),
    REPLACE_INVOICE("ReplaceInvoice"),
    DELETE_INVOICE("DeleteInvoice"),
    FIND_INVOICE("FindInvoice");

    private final String name;
}
