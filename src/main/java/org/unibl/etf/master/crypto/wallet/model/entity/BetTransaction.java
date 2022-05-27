package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.unibl.etf.master.crypto.wallet.enums.BetType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bet_transaction", schema = "wallet", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
public class BetTransaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "amount")
    private double amount;
    @Basic
    @Column(name = "bet_type")
    private int betType;
    @Basic
    @Column(name = "wallet_hash")
    private String walletHash;

    public BetTransaction(BetType betType, double amount, String walletHash) {
        this.betType = betType.getType();
        this.amount = amount;
        this.walletHash = walletHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBetType() {
        return betType;
    }

    public void setBetType(int betType) {
        this.betType = betType;
    }

    public String getWalletHash() {
        return walletHash;
    }

    public void setWalletHash(String walletHash) {
        this.walletHash = walletHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetTransaction that = (BetTransaction) o;
        return id == that.id && betType == that.betType && Objects.equals(amount, that.amount) && Objects.equals(walletHash, that.walletHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, betType, walletHash);
    }
}
