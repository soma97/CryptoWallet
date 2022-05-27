package org.unibl.etf.master.crypto.wallet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.master.crypto.wallet.model.request.BetRequest;
import org.unibl.etf.master.crypto.wallet.model.request.RollbackRequest;
import org.unibl.etf.master.crypto.wallet.model.request.WinRequest;
import org.unibl.etf.master.crypto.wallet.model.response.BetTransactionResponse;
import org.unibl.etf.master.crypto.wallet.service.GamingService;

import javax.validation.Valid;

@RestController
@RequestMapping("gaming")
public class GamingController {
    private final GamingService gamingService;

    public GamingController(GamingService gamingService) {
        this.gamingService = gamingService;
    }

    @PostMapping("bet")
    public ResponseEntity<BetTransactionResponse> bet(@RequestBody @Valid BetRequest betRequest) {
        return ResponseEntity.ok(gamingService.bet(betRequest));
    }

    @PostMapping("win")
    public ResponseEntity<BetTransactionResponse> win(@RequestBody @Valid WinRequest winRequest) {
        return ResponseEntity.ok(gamingService.win(winRequest));
    }

    @PostMapping("rollback")
    public ResponseEntity<BetTransactionResponse> rollback(@RequestBody @Valid RollbackRequest rollbackRequest) {
        return ResponseEntity.ok(gamingService.rollback(rollbackRequest));
    }
}
