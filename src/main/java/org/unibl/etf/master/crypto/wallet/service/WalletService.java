package org.unibl.etf.master.crypto.wallet.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.unibl.etf.master.crypto.wallet.configuration.CacheConfiguration;
import org.unibl.etf.master.crypto.wallet.enums.TransactionType;
import org.unibl.etf.master.crypto.wallet.exception.EntityAlreadyExistsException;
import org.unibl.etf.master.crypto.wallet.exception.NotEnoughFundsException;
import org.unibl.etf.master.crypto.wallet.exception.NotFoundException;
import org.unibl.etf.master.crypto.wallet.exception.ThirdPartyOperationException;
import org.unibl.etf.master.crypto.wallet.model.entity.Transaction;
import org.unibl.etf.master.crypto.wallet.model.entity.Wallet;
import org.unibl.etf.master.crypto.wallet.model.mapper.WalletMapper;
import org.unibl.etf.master.crypto.wallet.model.request.TransactionRequest;
import org.unibl.etf.master.crypto.wallet.model.request.WalletRequest;
import org.unibl.etf.master.crypto.wallet.model.response.WalletResponse;
import org.unibl.etf.master.crypto.wallet.repository.TransactionRepository;
import org.unibl.etf.master.crypto.wallet.repository.WalletRepository;
import java.util.Random;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final WalletMapper walletMapper;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.walletMapper = walletMapper;
    }

    @Cacheable(value = CacheConfiguration.WALLETS_CACHE_NAME, key = "#hash")
    public WalletResponse retrieveWallet(String hash) {
        return walletRepository.findById(hash)
                .map(walletMapper::toView)
                .orElseThrow(() -> new NotFoundException(Wallet.class, hash));
    }

    public WalletResponse createWallet(WalletRequest walletRequest) {
        if (walletRepository.exists(Example
                .of(new Wallet(null, walletRequest.virtualCurrency(), null, walletRequest.playerId())))) {
            throw new EntityAlreadyExistsException(Wallet.class);
        }

        // create wallet on blockchain integration
        boolean success = true;
        if (!success) {
            throw new ThirdPartyOperationException(Wallet.class, "Unknown reason");
        }

        Wallet newWallet = new Wallet("randomHash" + new Random().nextInt(999),
                walletRequest.virtualCurrency(), 0L, walletRequest.playerId());

        return walletMapper.toView(walletRepository.save(newWallet));
    }

    @CacheEvict(value = CacheConfiguration.WALLETS_CACHE_NAME, key = "#walletHash")
    public WalletResponse withdrawCrypto(String walletHash, TransactionRequest transactionRequest) {
        Wallet wallet = walletRepository.findById(walletHash)
                .orElseThrow(() -> new NotFoundException(Wallet.class, walletHash));

        if (wallet.getVirtualCredit() < transactionRequest.amount()) {
            throw new NotEnoughFundsException(Wallet.class, walletHash);
        }

        // add transaction to blockchain
        boolean success = true;
        if (!success) {
            throw new ThirdPartyOperationException(Transaction.class, "Unknown reason");
        }

        transactionRepository.save(new Transaction(TransactionType.WITHDRAW.getType(),
                "randomTransactionHash" + new Random().nextInt(999), walletHash));

        wallet.setVirtualCredit(wallet.getVirtualCredit() - transactionRequest.amount());
        walletRepository.save(wallet);
        return walletMapper.toView(wallet);
    }

}
