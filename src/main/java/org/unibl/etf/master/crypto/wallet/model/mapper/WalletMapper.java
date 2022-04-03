package org.unibl.etf.master.crypto.wallet.model.mapper;

import org.springframework.stereotype.Component;
import org.unibl.etf.master.crypto.wallet.model.entity.Wallet;
import org.unibl.etf.master.crypto.wallet.model.response.WalletResponse;

/***
 * Using custom mapper because MapStruct doesn't work well with records
 */
@Component
public class WalletMapper {

    public WalletResponse toView(Wallet wallet){
        return new WalletResponse(wallet.getHash(), wallet.getCurrency(), wallet.getVirtualCredit(), wallet.getPlayerId());
    }
}