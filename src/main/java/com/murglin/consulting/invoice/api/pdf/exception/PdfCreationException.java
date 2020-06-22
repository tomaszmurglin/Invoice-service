package com.murglin.consulting.invoice.api.pdf.exception;

public class PdfCreationException extends RuntimeException {
    public PdfCreationException(Throwable e) {
        super(e);
    }
}
