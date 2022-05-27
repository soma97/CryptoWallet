package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @Column(name = "hash")
    private String hash;
    @Basic
    @Column(name = "currency")
    private String currency;
    @Basic
    @Column(name = "virtual_credit")
    private double virtualCredit;
    @Basic
    @Column(name = "player_id")
    private int playerId;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getVirtualCredit() {
        return virtualCredit;
    }

    public void setVirtualCredit(double virtualCredit) {
        this.virtualCredit = virtualCredit;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return playerId == wallet.playerId && Objects.equals(hash, wallet.hash) && Objects.equals(currency, wallet.currency) && Objects.equals(virtualCredit, wallet.virtualCredit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, currency, virtualCredit, playerId);
    }
}
