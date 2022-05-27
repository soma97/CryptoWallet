package org.unibl.etf.master.crypto.wallet.service;

import org.springframework.stereotype.Service;
import org.unibl.etf.master.crypto.wallet.enums.BetType;
import org.unibl.etf.master.crypto.wallet.exception.NotEnoughFundsException;
import org.unibl.etf.master.crypto.wallet.exception.NotFoundException;
import org.unibl.etf.master.crypto.wallet.model.entity.BetTransaction;
import org.unibl.etf.master.crypto.wallet.model.entity.Wallet;
import org.unibl.etf.master.crypto.wallet.model.mapper.BetTransactionMapper;
import org.unibl.etf.master.crypto.wallet.model.request.BetRequest;
import org.unibl.etf.master.crypto.wallet.model.request.RollbackRequest;
import org.unibl.etf.master.crypto.wallet.model.request.WinRequest;
import org.unibl.etf.master.crypto.wallet.model.response.BetTransactionResponse;
import org.unibl.etf.master.crypto.wallet.repository.BetTransactionRepository;
import org.unibl.etf.master.crypto.wallet.repository.WalletRepository;

@Service
public class GamingService {
    private final WalletRepository walletRepository;
    private final BetTransactionRepository betTransactionRepository;
    private final BetTransactionMapper betTransactionMapper;

    public GamingService(WalletRepository walletRepository, BetTransactionRepository betTransactionRepository, BetTransactionMapper betTransactionMapper) {
        this.walletRepository = walletRepository;
        this.betTransactionRepository = betTransactionRepository;
        this.betTransactionMapper = betTransactionMapper;
    }

    public BetTransactionResponse bet(BetRequest betRequest){
        Wallet wallet = walletRepository.findById(betRequest.walletHash())
                .orElseThrow(() -> new NotFoundException(Wallet.class, betRequest.walletHash()));

        if (wallet.getVirtualCredit() < betRequest.amount()) {
            throw new NotEnoughFundsException(Wallet.class, betRequest.walletHash());
        }
        BetTransaction betTransaction = betTransactionRepository.save(new BetTransaction(BetType.BET, betRequest.amount(), wallet.getHash()));

        wallet.setVirtualCredit(wallet.getVirtualCredit() - betRequest.amount());
        walletRepository.save(wallet);

        return betTransactionMapper.toView(betTransaction);
    }

    public BetTransactionResponse win(WinRequest winRequest){
        Wallet wallet = walletRepository.findById(winRequest.walletHash())
                .orElseThrow(() -> new NotFoundException(Wallet.class, winRequest.walletHash()));
        BetTransaction betTransaction = betTransactionRepository.save(new BetTransaction(BetType.WIN, winRequest.amount(), wallet.getHash()));

        wallet.setVirtualCredit(wallet.getVirtualCredit() + winRequest.amount());
        walletRepository.save(wallet);

        return betTransactionMapper.toView(betTransaction);
    }

    public BetTransactionResponse rollback(RollbackRequest rollbackRequest) {
        BetTransaction betTransaction = betTransactionRepository.findById(rollbackRequest.betId())
                .orElseThrow(() -> new NotFoundException(BetTransaction.class, rollbackRequest.betId()));

        if(betTransaction.getBetType() == BetType.BET_ROLLBACK.getType() || betTransaction.getBetType() == BetType.WIN_ROLLBACK.getType())
            throw new NotFoundException(BetTransaction.class, rollbackRequest.betId());

        Wallet wallet = walletRepository.findById(betTransaction.getWalletHash())
                .orElseThrow(() -> new NotFoundException(Wallet.class, betTransaction.getWalletHash()));

        BetTransaction rollbackTransaction = null;
        if(betTransaction.getBetType() == BetType.BET.getType()){
            wallet.setVirtualCredit(wallet.getVirtualCredit() + betTransaction.getAmount());
            rollbackTransaction = betTransactionRepository.save(new BetTransaction(BetType.BET_ROLLBACK, betTransaction.getAmount(), wallet.getHash()));
        } else if(betTransaction.getBetType() == BetType.WIN.getType()){
            wallet.setVirtualCredit(wallet.getVirtualCredit() - betTransaction.getAmount());
            rollbackTransaction = betTransactionRepository.save(new BetTransaction(BetType.WIN_ROLLBACK, betTransaction.getAmount(), wallet.getHash()));
        }
        walletRepository.save(wallet);
        return betTransactionMapper.toView(rollbackTransaction);
    }
}