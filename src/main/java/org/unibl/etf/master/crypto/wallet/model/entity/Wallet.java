package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Wallet {
    @Id
    @Column(name = "hash")
    private String hash;
    @Basic
    @Column(name = "currency")
    private String currency;
    @Basic
    @Column(name = "player_id")
    private int playerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return playerId == wallet.playerId && Objects.equals(hash, wallet.hash) && Objects.equals(currency, wallet.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, currency, playerId);
    }
}
