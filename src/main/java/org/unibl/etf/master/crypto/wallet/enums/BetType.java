package org.unibl.etf.master.crypto.wallet.enums;

import lombok.Getter;

public enum BetType {
    BET(1), WIN(2), BET_ROLLBACK(41), WIN_ROLLBACK(42);

    @Getter
    private final int type;

    BetType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
