package org.unibl.etf.master.crypto.wallet.model.response;

public record WalletResponse(String hash, String currency, double virtualCredit, int playerId) {
}
