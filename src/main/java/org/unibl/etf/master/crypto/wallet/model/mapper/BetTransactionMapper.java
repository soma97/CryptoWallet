package org.unibl.etf.master.crypto.wallet.model.mapper;

import org.springframework.stereotype.Component;
import org.unibl.etf.master.crypto.wallet.model.entity.BetTransaction;
import org.unibl.etf.master.crypto.wallet.model.response.BetTransactionResponse;

/***
 * Using custom mapper because MapStruct doesn't work well with records
 */
@Component
public class BetTransactionMapper {

    public BetTransactionResponse toView(BetTransaction betTransaction){
        return new BetTransactionResponse(betTransaction.getId(), betTransaction.getWalletHash(), betTransaction.getAmount());
    }
}
