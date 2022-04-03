package org.unibl.etf.master.crypto.wallet.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record WalletRequest(@NotNull Integer playerId, @NotBlank @Size(min = 2, max = 4) String virtualCurrency) { }
