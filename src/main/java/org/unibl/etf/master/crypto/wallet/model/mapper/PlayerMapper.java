package org.unibl.etf.master.crypto.wallet.model.mapper;

import org.springframework.stereotype.Component;
import org.unibl.etf.master.crypto.wallet.model.entity.Player;
import org.unibl.etf.master.crypto.wallet.model.request.PlayerRequest;
import org.unibl.etf.master.crypto.wallet.model.response.PlayerResponse;

/***
 * Using custom mapper because MapStruct doesn't work well with records
 */
@Component
public class PlayerMapper {

    public Player toModel(PlayerRequest playerRequest){
        return new Player(playerRequest.currency(), playerRequest.credit(), playerRequest.externalId());
    }

    public PlayerResponse toView(Player player){
        return new PlayerResponse(player.getId(), player.getCurrency(), player.getCredit(), player.getExternalId());
    }
}
