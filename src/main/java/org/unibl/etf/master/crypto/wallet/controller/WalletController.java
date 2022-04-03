package org.unibl.etf.master.crypto.wallet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.unibl.etf.master.crypto.wallet.model.request.TransactionRequest;
import org.unibl.etf.master.crypto.wallet.model.request.WalletRequest;
import org.unibl.etf.master.crypto.wallet.model.response.WalletResponse;
import org.unibl.etf.master.crypto.wallet.service.WalletService;
import javax.validation.Valid;

@RestController
@RequestMapping("wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController( WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{hash}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable("hash") String hash) {
        return ResponseEntity.ok(walletService.retrieveWallet(hash));
    }

    @PostMapping
    public ResponseEntity<WalletResponse> postWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletResponse walletResponse = walletService.createWallet(walletRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{hash}")
                .buildAndExpand(walletResponse.hash()).toUri()).build();
    }

    @PostMapping("/{hash}/withdraw")
    public ResponseEntity<WalletResponse> withdrawCrypto(@PathVariable("hash") String walletHash, @RequestBody @Valid TransactionRequest transactionRequest) {
        return ResponseEntity.ok(walletService.withdrawCrypto(walletHash, transactionRequest));
    }

}
