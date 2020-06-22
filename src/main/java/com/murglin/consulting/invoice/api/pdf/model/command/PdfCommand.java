package com.murglin.consulting.invoice.api.pdf.model.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PdfCommand {

    CREATE_PDF("CreatePdf"),
    CANCEL_PDF_GENERATING("CancelPdfGenerating"),
    DELETE_PDF("DeletePdf"),
    FIND_PDF("FindPdf");

    private final String name;
}
