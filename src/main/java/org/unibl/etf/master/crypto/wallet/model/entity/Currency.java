package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Currency {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "currency")
    private String currency;
    @Basic
    @Column(name = "real")
    private Byte real;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Byte getReal() {
        return real;
    }

    public void setReal(Byte real) {
        this.real = real;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency1 = (Currency) o;
        return Objects.equals(currency, currency1.currency) && Objects.equals(real, currency1.real);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, real);
    }
}
