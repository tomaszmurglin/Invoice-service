package com.murglin.consulting.invoice.api;

import com.murglin.consulting.invoice.api.crud.Invoice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryStorage {

    public final static Map<UUID, Invoice> ID_TO_INVOICE = new ConcurrentHashMap<>();
    public final static Map<UUID, byte[]> ID_TO_PDF = new ConcurrentHashMap<>();
}
