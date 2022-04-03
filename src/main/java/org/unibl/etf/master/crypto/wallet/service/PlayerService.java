package org.unibl.etf.master.crypto.wallet.service;

import org.springframework.stereotype.Service;
import org.unibl.etf.master.crypto.wallet.exception.NotFoundException;
import org.unibl.etf.master.crypto.wallet.model.entity.Player;
import org.unibl.etf.master.crypto.wallet.model.mapper.PlayerMapper;
import org.unibl.etf.master.crypto.wallet.model.request.PlayerRequest;
import org.unibl.etf.master.crypto.wallet.model.response.PlayerResponse;
import org.unibl.etf.master.crypto.wallet.repository.PlayerRepository;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerResponse retrievePlayer(int id) {
        return playerRepository.findById(id)
                .map(playerMapper::toView)
                .orElseThrow(() -> new NotFoundException(Player.class, id));
    }

    public PlayerResponse createPlayer(PlayerRequest playerRequest) {
        return playerMapper.toView(playerRepository.save(playerMapper.toModel(playerRequest)));
    }

    public void deletePlayer(int id) {
        if(!playerRepository.existsById(id)) {
            throw new NotFoundException(Player.class, id);
        }
        playerRepository.deleteById(id);
    }
}
