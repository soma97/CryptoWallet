package org.unibl.etf.master.crypto.wallet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.unibl.etf.master.crypto.wallet.model.request.PlayerRequest;
import org.unibl.etf.master.crypto.wallet.model.response.PlayerResponse;
import org.unibl.etf.master.crypto.wallet.service.PlayerService;
import javax.validation.Valid;

@RestController
@RequestMapping("player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayer(@PathVariable("id") int id) {
        return ResponseEntity.ok(playerService.retrievePlayer(id));
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> postPlayer(@RequestBody @Valid PlayerRequest playerRequest) {
        PlayerResponse playerResponse = playerService.createPlayer(playerRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(playerResponse.id()).toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlayerResponse> deletePlayer(@PathVariable("id") int id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
