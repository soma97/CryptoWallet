package org.unibl.etf.master.crypto.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.master.crypto.wallet.model.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> { }