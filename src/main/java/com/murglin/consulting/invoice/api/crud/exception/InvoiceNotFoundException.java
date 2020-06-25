package com.murglin.consulting.invoice.api.crud.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public static final String INVOICE_DOESNT_EXIST = "Such invoice doesnt exist";

    public InvoiceNotFoundException() {
        super(INVOICE_DOESNT_EXIST);
    }
}
