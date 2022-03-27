package org.unibl.etf.master.crypto.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.master.crypto.wallet.model.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> { }