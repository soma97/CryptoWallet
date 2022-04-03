package org.unibl.etf.master.crypto.wallet.enums;

import lombok.Getter;

public enum TransactionType {
    WITHDRAW(1), DEPOSIT(2);

    @Getter
    private final int type;

    TransactionType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type == WITHDRAW.getType() ? "Withdraw" : "Deposit";
    }
}
