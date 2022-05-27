package org.unibl.etf.master.crypto.wallet.model.response;

public record BetTransactionResponse(int betId, String walletHash, double amount) {}
