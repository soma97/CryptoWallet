package org.unibl.etf.master.crypto.wallet.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Player(String currency, long credit, String externalId) {
        this.currency = currency;
        this.credit = credit;
        this.externalId = externalId;
    }

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
