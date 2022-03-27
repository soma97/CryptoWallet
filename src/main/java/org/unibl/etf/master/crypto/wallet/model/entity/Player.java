package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Player {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "currency")
    private String currency;
    @Basic
    @Column(name = "credit")
    private Long credit;
    @Basic
    @Column(name = "external_id")
    private String externalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && Objects.equals(currency, player.currency) && Objects.equals(credit, player.credit) && Objects.equals(externalId, player.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, credit, externalId);
    }
}
