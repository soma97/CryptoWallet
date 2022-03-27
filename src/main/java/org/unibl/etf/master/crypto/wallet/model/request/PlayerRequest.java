package org.unibl.etf.master.crypto.wallet.model.request;

public record PlayerRequest(int id, String currency, long credit, String externalId) {}