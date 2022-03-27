package org.unibl.etf.master.crypto.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.master.crypto.wallet.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> { }