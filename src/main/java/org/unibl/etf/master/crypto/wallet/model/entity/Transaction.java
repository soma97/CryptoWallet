package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Basic
    @Column(name = "type")
    private int type;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "hash")
    private String hash;
    @Basic
    @Column(name = "wallet_hash")
    private String walletHash;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
        Transaction that = (Transaction) o;
        return type == that.type && Objects.equals(hash, that.hash) && Objects.equals(walletHash, that.walletHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, hash, walletHash);
    }
}
